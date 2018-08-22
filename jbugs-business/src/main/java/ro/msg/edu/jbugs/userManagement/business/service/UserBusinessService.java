package ro.msg.edu.jbugs.userManagement.business.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.RoleDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.UserDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.business.utils.Encryptor;
import ro.msg.edu.jbugs.userManagement.persistence.service.IUserPersistenceService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Stateless
public class UserBusinessService implements IUserBusinessService {


    private final static int MAX_LAST_NAME_LENGTH = 5;
    private final static int MIN_USERNAME_LENGTH = 6;
    private static final Logger logger = LogManager.getLogger(UserBusinessService.class);


    @EJB
    private IUserPersistenceService userPersistenceManager;

    /**
     * Creates a user entity using a user DTO.
     *
     * @param userDTO user information
     * @return : the user DTO of the created entity
     * @throws BusinessException
     */

    @Override
    public UserDTO createUser(UserDTO userDTO) throws BusinessException {

        logger.log(Level.INFO, "In createUser method");
        normalizeUserDTO(userDTO);
        validateUserForCreation(userDTO);
        User user = UserDTOHelper.toEntity(userDTO);
        user.setUsername(generateFullUsername(userDTO.getFirstName(), userDTO.getLastName()));
        user.setActive(true);
        user.setPassword(Encryptor.encrypt(generatePassword(user.getUsername())));
        userPersistenceManager.createUser(user);
        return UserDTOHelper.fromEntity(user);
    }


    /**
     * Validates the DTO. To use before sending it further.
     *
     * @param userDTO
     * @throws BusinessException
     */
    private void validateUserForCreation(UserDTO userDTO) throws BusinessException {
        if (!isValidForCreation(userDTO)) {
            throw new BusinessException(ExceptionCode.USER_VALIDATION_EXCEPTION);
        }
        //validate if email already exists
        if (userPersistenceManager.getUserByEmail(userDTO.getEmail()).isPresent()) {
            throw new BusinessException(ExceptionCode.EMAIL_EXISTS_ALREADY);
        }


    }

    /**
     * Trims stuff (first and last name)
     *
     * @param userDTO
     */
    private void normalizeUserDTO(UserDTO userDTO) {
        userDTO.setFirstName(userDTO.getFirstName().trim());
        userDTO.setLastName(userDTO.getLastName().trim());
    }

    /**
     * Creates a suffix for the username, if the username already exists. The suffix consists
     * of a number.
     *
     * @param username
     * @return
     */
    protected String createSuffix(String username) {

        Optional<Integer> max = userPersistenceManager.getUsernamesLike(username)
                .stream()
                .map(x -> x.substring(MIN_USERNAME_LENGTH, x.length()))
                .map(x -> x.equals("") ? 0 : Integer.parseInt(x))
                .max(Comparator.naturalOrder())
                .map(x -> x + 1);
        return max.map(Object::toString).orElse("");
    }

    private boolean isValidForCreation(UserDTO user) {
        return user.getEmail() != null
                && user.getLastName() != null
                && user.getEmail() != null
                && user.getPassword() != null
                && isValidEmail(user.getEmail());
    }

    private boolean isValidEmail(String email) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@msggroup.com$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }


    /**
     * Generates a username, taking the first 5 letters of the last name and the first
     * letter of the first name.
     * If the user's last name is not long enough it will try
     * to add the first name's letters to the username until it has 6 characters.
     * If the username already exists it will append a number to the username.
     *
     * @param firstName
     * @param lastName
     * @return generated username
     */
    protected String generateUsername(@NotNull final String firstName, @NotNull final String lastName) {
        StringBuilder username = new StringBuilder();


        if (lastName.length() >= MAX_LAST_NAME_LENGTH) {
            username.append(lastName.substring(0, MAX_LAST_NAME_LENGTH) + firstName.charAt(0));

        } else if (lastName.length() + firstName.length() >= MIN_USERNAME_LENGTH) {
            username.append(lastName + firstName.substring(0, MIN_USERNAME_LENGTH - lastName.length()));
        } else {
            username.append(lastName + firstName);
            int usernameLength = username.length();
            for (int i = 0; i < MIN_USERNAME_LENGTH - usernameLength; i++) {
                username.append("0");
            }
        }


        return username.toString().toLowerCase();

    }

    /**
     * Deactivates a user, removing them the ability to login, but keeping their bugs, comments, etc.
     *
     * @param username
     */
    @Override
    public void deactivateUser(String username) {
        User user = userPersistenceManager.getUserByUsername(username).get();
        user.setActive(false);
        userPersistenceManager.updateUser(user);
    }

    /**
     * Activates a user, granting them the ability to login.
     *
     * @param username
     */
    @Override
    public void activateUser(String username) {
        User user = userPersistenceManager.getUserByUsername(username).get();
        user.setActive(true);
        userPersistenceManager.updateUser(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return UserDTOHelper.fromEntity(userPersistenceManager.updateUser(UserDTOHelper.toEntity(userDTO)).get());
    }

    @Override
    public UserDTO getUserByUsername(String username) throws BusinessException {
        validateUserName(username);
        return UserDTOHelper.fromEntity(userPersistenceManager.getUserByUsername(username).get());
    }

    /**
     * Get a list of all Users that are registered.
     *
     * @return
     */
    @Override
    public List<UserDTO> getAllUsers() {
        return userPersistenceManager.getAllUsers()
                .stream()
                .map(UserDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) throws BusinessException {
        Role role = RoleDTOHelper.toEntity(roleDTO);
        userPersistenceManager.createRole(role);
        return RoleDTOHelper.fromEntity(role);
    }

    @Override
    public RoleDTO getRoleById(long id) {
        Role role = userPersistenceManager.getRoleForId(id).get();
        return RoleDTOHelper.fromEntity(role);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = userPersistenceManager.getAllRoles();
        return roles.stream().map(x -> RoleDTOHelper.fromEntity(x)).collect(Collectors.toList());
    }

    private String generateFullUsername(String firstName, String lastName) {
        String prefix = generateUsername(firstName, lastName);
        String suffix = createSuffix(prefix);
        return prefix + suffix;
    }

    private boolean isValidPhoneNumber(String phonenumber) {
        //TODO Nu merge
        final Pattern VALID_PHONE_ADDRESS_REGEX =
                Pattern.compile("(^\\+49)|(^01[5-7][1-9])", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_PHONE_ADDRESS_REGEX.matcher(phonenumber);
        return matcher.find();
    }


    private String generatePassword(String password) {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        return password + c;
    }


    private void validateUserName(String userName) throws BusinessException {
        if (!userPersistenceManager.getUserByUsername(userName).isPresent())
            throw new BusinessException(ExceptionCode.USERNAME_NOT_VALID);
    }
}
