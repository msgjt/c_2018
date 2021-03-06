package ro.msg.edu.jbugs.userManagement.business.service.user;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.RoleDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.UserDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserChangePasswordDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.business.service.utils.Encryptor;
import ro.msg.edu.jbugs.userManagement.business.service.notification.NotificationBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.NotificationEnum;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.service.IUserPersistenceService;

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
    private IUserPersistenceService userPersistenceService;
    @EJB
    private UserDTOHelper userDTOHelper;
    @EJB
    private NotificationBusinessService notificationBusinessService;

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
        User user = userDTOHelper.toEntity(userDTO);
        user.setUsername(generateFullUsername(userDTO.getFirstName(), userDTO.getLastName()));
        user.setIsActive(true);
        user.setPassword(Encryptor.encrypt(generatePassword(user.getUsername())));
        userPersistenceService.createUser(user);
        UserDTO userDTOAfterPersist = userDTOHelper.fromEntity(user);
        notificationBusinessService.generateNotification(NotificationEnum.WELCOME_NEW_USER, null, userDTOAfterPersist);
        return userDTOAfterPersist;
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
        if (userPersistenceService.getUserByEmail(userDTO.getEmail()).isPresent()) {
            throw new BusinessException(ExceptionCode.EMAIL_EXISTS_ALREADY);
        }


    }

    private void validateUserForUpdate(UserDTO userDTO) throws BusinessException {
        if (!isValidForCreation(userDTO)) {
            throw new BusinessException(ExceptionCode.USER_VALIDATION_EXCEPTION);
        }
        //validate if email already exists
        if (userPersistenceService.getUserByEmail(userDTO.getEmail()).isPresent() && !userPersistenceService.getUserByUsername(userDTO.getUsername()).get().getEmail().equals(userDTO.getEmail())) {
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
    public String createSuffix(String username) {

        Optional<Integer> max = userPersistenceService.getUsernamesLike(username)
                .stream()
                .map(x -> x.substring(MIN_USERNAME_LENGTH, x.length()))
                .map(x -> x.equals("") ? 0 : Integer.parseInt(x))
                .max(Comparator.naturalOrder())
                .map(x -> x + 1);
        return max.map(Object::toString).orElse("");
    }

    private boolean isValidForCreation(UserDTO user) {
        return user.getFirstName() != null
                && user.getLastName() != null
                && user.getEmail() != null
                && user.getPhoneNumber() != null
                && user.getRoles().size() != 0
                && isValidFirstName(user.getFirstName())
                && isValidLastName(user.getLastName())
                && isValidEmail(user.getEmail())
                && isValidPhoneNumber(user.getPhoneNumber());
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
    public String generateUsername(@NotNull final String firstName, @NotNull final String lastName) {
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
        User user = userPersistenceService.getUserByUsername(username).get();
        user.setIsActive(false);
        userPersistenceService.updateUser(user);
    }

    /**
     * Activates a user, granting them the ability to login.
     *
     * @param username
     */
    @Override
    public void activateUser(String username) {
        User user = userPersistenceService.getUserByUsername(username).get();
        user.setIsActive(true);
        userPersistenceService.updateUser(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws BusinessException {
        validateUserForUpdate(userDTO);
        if (!userDTO.getIsActive() && userPersistenceService.countUnfinishedTasks(userDTOHelper.toEntity(userDTO)) != 0) {
            throw new BusinessException(ExceptionCode.UNFINISHED_TASKS);
        }
        return userDTOHelper.fromEntity(userPersistenceService.updateUser(userDTOHelper.toEntity(userDTO)).get());
    }

    @Override
    public UserDTO getUserByUsername(String username) throws BusinessException {
        validateUserName(username);
        return userDTOHelper.fromEntity(userPersistenceService.getUserByUsername(username).get());
    }

    @Override
    public void changePassword(UserChangePasswordDTO userChangePasswordDTO) throws BusinessException {
        validateUserName(userChangePasswordDTO.getUsername());
        if (!userPersistenceService.getUserByUsername(userChangePasswordDTO.getUsername()).get().getPassword().equals(Encryptor.encrypt(userChangePasswordDTO.getOldPassword()))) {
            throw new BusinessException(ExceptionCode.PASSWORD_NOT_VALID);
        } else {
            userPersistenceService.changePassword(userChangePasswordDTO.getUsername(), Encryptor.encrypt(userChangePasswordDTO.getNewPassword()));
        }
    }

    /**
     * Get a list of all Users that are registered.
     *
     * @return
     */
    @Override
    public List<UserDTO> getAllUsers() {
        return userPersistenceService.getAllUsers()
                .stream()
                .map(userDTOHelper::fromEntity)
                .collect(Collectors.toList());
    }

    private String generateFullUsername(String firstName, String lastName) {
        String prefix = generateUsername(firstName, lastName);
        String suffix = createSuffix(prefix);
        return prefix + suffix;
    }

    private boolean isValidPhoneNumber(String phonenumber) {
        final Pattern VALID_PHONE_REGEX =
                Pattern.compile("((^\\+40|^0|^\\(\\+40\\)|^0040)((7[2-8][0-9]{7}$)))|(^(^\\+49|^\\(\\+49\\)|^0049)1(5|6|7)[0-9]{8,10}$)", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_PHONE_REGEX.matcher(phonenumber);
        return matcher.find();
    }

    private boolean isValidFirstName(String firstName) {
        final Pattern VALID_FIRSTNAME_REGEX =
                Pattern.compile("^([a-zA-Z]+(\\s?|-?)[a-zA-Z]+){1,5}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_FIRSTNAME_REGEX.matcher(firstName);
        return matcher.find();
    }

    private boolean isValidLastName(String lastName) {
        final Pattern VALID_LASTNAME_REGEX =
                Pattern.compile("^([a-zA-Z]+(\\s?|-?)[a-zA-Z]+){1,5}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_LASTNAME_REGEX.matcher(lastName);
        return matcher.find();
    }


    private String generatePassword(String password) {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        return password + c;
    }


    private void validateUserName(String userName) throws BusinessException {
        if (!userPersistenceService.getUserByUsername(userName).isPresent())
            throw new BusinessException(ExceptionCode.USERNAME_NOT_VALID);
    }
}
