package ro.msg.edu.jbugs.business.service;

import org.junit.Before;
import ro.msg.edu.jbugs.business.dto.helper.RoleDTOHelper;
import ro.msg.edu.jbugs.business.dto.helper.UserDTOHelper;
import ro.msg.edu.jbugs.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.business.dto.user.UserSessionDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.service.user.RoleBusinessService;
import ro.msg.edu.jbugs.business.service.user.UserBusinessService;
import ro.msg.edu.jbugs.business.service.user.LoginBusinessService;
import ro.msg.edu.jbugs.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.business.service.utils.Encryptor;
import ro.msg.edu.jbugs.business.service.utils.JwtService;
import ro.msg.edu.jbugs.persistence.entity.*;
import ro.msg.edu.jbugs.persistence.exceptions.PersistenceException;
import ro.msg.edu.jbugs.persistence.service.user.IRolePersistenceService;
import ro.msg.edu.jbugs.persistence.service.user.UserPersistenceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.constraints.Null;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserPersistenceServiceBeanTest {

    private static final String MARINI = "marini";
    private static final String DOREL0 = "dorel0";
    @InjectMocks
    private UserBusinessService userBusinessService;

    @InjectMocks
    private RoleBusinessService roleBusinessService;

    @InjectMocks
    private LoginBusinessService loginBusinessService;

    @Mock
    private IRolePersistenceService rolePersistenceService;

    @Mock
    private UserDTOHelper userDTOHelper;

    @Mock
    private JwtService jwtService;
    @Mock
    private UserPersistenceService userPersistenceService;




    @Test
    public void generateUsername_expectedMarini() {
        String username = userBusinessService.generateUsername("Ion", "Marin");
        assertEquals(MARINI, username);
    }

    @Test
    public void generateUsername_expectedIonion() {
        String username = userBusinessService.generateUsername("Ion", "Ion");
        assertEquals("ionion", username);
    }
    @Test
    public void generateUsername_expectedPetric() {
        String username = userBusinessService.generateUsername("Calin", "Petrindean");
        assertEquals("petric", username);
    }

    @Test
    public void generateUsername_expectedba0000() {
        String username = userBusinessService.generateUsername("a", "b");
        assertEquals("ba0000", username);
    }

    @Test
    public void createSuffix_expectedEmpty(){

        when(userPersistenceService.getUsernamesLike(any(String.class))).thenReturn(new HashSet<>());
        String suffix = userBusinessService.createSuffix(DOREL0);
        assertEquals( "",suffix);

    }

    @Test
    public void createSuffix_expected4(){


        when(userPersistenceService.getUsernamesLike(any(String.class)))
                .thenReturn(
                        new HashSet<String>(){{
                            add(DOREL0);
                            add("dorel01");
                            add("dorel02");
                            add("dorel03");
                        }}
                );
        String suffix = userBusinessService.createSuffix(DOREL0);
        assertEquals( "4",suffix);

    }

    @Test
    public void createSuffix_expected7(){


        when(userPersistenceService.getUsernamesLike(any(String.class)))
                .thenReturn(
                        new HashSet<String>(){{
                            add(DOREL0);
                            add("dorel06");
                        }}
                );
        String suffix = userBusinessService.createSuffix(DOREL0);
        assertEquals("7",suffix);

    }

    @Test
    public void createSuffix_expected1(){


        when(userPersistenceService.getUsernamesLike(any(String.class)))
                .thenReturn(
                        new HashSet<String>(){{
                            add(MARINI);
                        }}
                );
        String suffix = userBusinessService.createSuffix(MARINI);
        assertEquals( "1",suffix);
    }

    @Test
    public void testLogin_wrongUsername() {

        UserDTO userDTO = new UserDTO();
        User user = new User();
        userDTO.setUsername("a");
        userDTO.setPassword("s");
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername(userDTO.getUsername());
        userLoginDTO.setPassword(userDTO.getPassword());
        when(userDTOHelper.toEntity(userDTO)).thenReturn(user);
        when(userDTOHelper.fromEntity(user)).thenReturn(userDTO);
        try {
            loginBusinessService.login(userLoginDTO);
            fail("Shouldn't reach this point");
        } catch (BusinessException e){
            assertEquals(ExceptionCode.USERNAME_NOT_VALID,e.getExceptionCode());
        } catch (NullPointerException e){
            assertNull(userPersistenceService.getUserByUsername(userLoginDTO.getUsername()));
        }
    }

    @Test
    public void testLogin_Success(){
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(Encryptor.encrypt("searatarziu"));
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setPassword("searatarziu");
        try {
            loginBusinessService.validatePassword(userLoginDTO,userDTO);
        } catch (BusinessException e) {
            assertNotEquals(e.getMessage(), ExceptionCode.INVALID_USER_LOGIN);
        }
    }

    @Test
    public void testGenerateToken_Success(){
        final UserSessionDTO userSessionDTO = new UserSessionDTO();
        Set<PermissionEnum> permissions = new HashSet<>();
        permissions.add(PermissionEnum.ADDRESSED_USER);
        userSessionDTO.setPermissions(permissions);
        when(jwtService.generateToken(userSessionDTO)).thenCallRealMethod();
        userSessionDTO.setUsername("abracadabra");
        String token = jwtService.generateToken(userSessionDTO);
        assertTrue(token.length()>0);
    }

    @Test
    public void testLogout_Success(){
        UserSessionDTO userSessionDTO = new UserSessionDTO();
        userSessionDTO.setUsername("dorel");
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("dorel");
        User user = new User();
        user.setUsername(userSessionDTO.getUsername());
        try {
            when(userPersistenceService.getUserByUsername(userSessionDTO.getUsername())).thenReturn(Optional.of(user));
            when(userBusinessService.getUserByUsername(userSessionDTO.getUsername())).thenReturn(userDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateUser_Success(){


        User user = new User();
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Cristi");
        userDTO.setLastName("Borcea");
        userDTO.setEmail("dinamo@msggroup.com");
        userDTO.setPhoneNumber("0755890163");
        userDTO.setPassword("IloveSteaua10");
        Set<RoleDTO> roles = new HashSet<>();
        RoleDTO role = new RoleDTO();
        roles.add(role);
        userDTO.setRoles(roles);
        when(userDTOHelper.toEntity(userDTO)).thenReturn(user);
        when(userDTOHelper.fromEntity(user)).thenReturn(userDTO);
        try{
        UserDTO createdUser = userBusinessService.createUser(userDTO);
        assertEquals(userDTO.getFirstName(),createdUser.getFirstName());
        assertEquals(userDTO.getLastName(),createdUser.getLastName());
        assertEquals(userDTO.getEmail(),createdUser.getEmail());
        assertEquals("borcec",createdUser.getUsername());
        } catch (BusinessException e){
            fail("Should not reach this point");
        } catch (NullPointerException e){
            assertNull(userPersistenceService.getUserByEmail(userDTO.getEmail()));
            assertNull(userPersistenceService.getUserByUsername(userDTO.getUsername()));
        }


    }

    @Test
    public void deactivateUser_ExpectedOk()
    {

        User user = new User();
        UserDTO userDTO = new UserDTO();
        when(userDTOHelper.toEntity(userDTO)).thenReturn(user);
        when(userDTOHelper.fromEntity(user)).thenReturn(userDTO);
        user.setUsername("florib");
        user.setIsActive(true);
        when(userPersistenceService.getUserByUsername(any(String.class)))
                .thenReturn(Optional.of(user));
        userBusinessService.deactivateUser("florib");
        assertFalse(user.getIsActive());
    }

    @Test
    public void activateUser_ExpectedOk()
    {

        User user = new User();
        UserDTO userDTO = new UserDTO();
        when(userDTOHelper.toEntity(userDTO)).thenReturn(user);
        when(userDTOHelper.fromEntity(user)).thenReturn(userDTO);
        user.setUsername("florib");
        user.setIsActive(false);
        when(userPersistenceService.getUserByUsername(any(String.class)))
                .thenReturn(Optional.of(user));
        userBusinessService.activateUser("florib");
        assertTrue(user.getIsActive());
    }

    @Test
    public void updateUser_ExpectedFail(){
        User user = new User();
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Gigi");
        userDTO.setLastName("Becali");
        userDTO.setEmail("steaua@msggroup.com");
        userDTO.setPhoneNumber("0755890163");
        userDTO.setPassword("IloveDinamo10");
        Set<RoleDTO> roles = new HashSet<>();
        RoleDTO role = new RoleDTO();
        roles.add(role);
        userDTO.setRoles(roles);
        when(userDTOHelper.toEntity(userDTO)).thenReturn(user);
        when(userPersistenceService.createUser(user)).thenReturn(Optional.of(user));
        when(userDTOHelper.fromEntity(user)).thenReturn(userDTO);
        try {
           when(userBusinessService.createUser(userDTO)).thenReturn(userDTO);
           UserDTO userToUpdate = userDTO;
           userToUpdate.setFirstName("Gigi2");
           userBusinessService.updateUser(userToUpdate);
            assertFalse(userToUpdate.getFirstName().equals(userDTO.getFirstName()));
        } catch (NullPointerException e) {
            assertNull(userPersistenceService.getUserByEmail(userDTO.getEmail()));
        } catch (BusinessException e) {
            e.printStackTrace();
        }

    }





}