package ro.msg.edu.jbugs.userManagement.business.service;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.RoleDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.business.service.bug.BugBusinessService;
import ro.msg.edu.jbugs.userManagement.business.service.bug.IBugBusinessService;
import ro.msg.edu.jbugs.userManagement.business.service.user.RoleBusinessService;
import ro.msg.edu.jbugs.userManagement.business.service.user.UserBusinessService;
import ro.msg.edu.jbugs.userManagement.business.service.user.UserLoginBusinessService;
import ro.msg.edu.jbugs.userManagement.business.service.utils.ByteToFilesConverter;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.RoleEnum;
import ro.msg.edu.jbugs.userManagement.persistence.service.IRolePersistenceService;
import ro.msg.edu.jbugs.userManagement.persistence.service.UserPersistenceService;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserPersistenceServiceBeanTest {

    @InjectMocks
    private UserBusinessService userBusinessService;

    @EJB
    private IBugBusinessService bugBusinessService;

    @InjectMocks
    private RoleBusinessService roleBusinessService;

    @InjectMocks
    private UserLoginBusinessService loginBusinessService;

    @Mock
    private IRolePersistenceService rolePersistenceService;
    @Mock
    private RoleDTOHelper roleDTOHelper;

    @Mock
    private UserPersistenceService userPersistenceService;


    @Test
    public void generateUsername_expectedMarini() {
        String username = userBusinessService.generateUsername("Ion", "Marin");
        assertEquals("marini", username);
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
        String suffix = userBusinessService.createSuffix("dorel0");
        assertEquals( "",suffix);

    }

    @Test
    public void createSuffix_expected4(){


        when(userPersistenceService.getUsernamesLike(any(String.class)))
                .thenReturn(
                        new HashSet<String>(){{
                            add("dorel0");
                            add("dorel01");
                            add("dorel02");
                            add("dorel03");
                        }}
                );
        String suffix = userBusinessService.createSuffix("dorel0");
        assertEquals( "4",suffix);

    }

    @Test
    public void createSuffix_expected7(){


        when(userPersistenceService.getUsernamesLike(any(String.class)))
                .thenReturn(
                        new HashSet<String>(){{
                            add("dorel0");
                            add("dorel06");
                        }}
                );
        String suffix = userBusinessService.createSuffix("dorel0");
        assertEquals("7",suffix);

    }

    @Test
    public void createSuffix_expected1(){


        when(userPersistenceService.getUsernamesLike(any(String.class)))
                .thenReturn(
                        new HashSet<String>(){{
                            add("marini");
                        }}
                );
        String suffix = userBusinessService.createSuffix("marini");
        assertEquals( "1",suffix);
    }

    @Test
    public void testLogin_wrongUsername() {
        when(userPersistenceService.getUserByUsername(any(String.class)))
                .thenReturn(Optional.empty());
        try {
            loginBusinessService.login(new UserLoginDTO("a","s") );
            fail("Shouldn't reach this point");
        } catch (BusinessException e){
            assertEquals(ExceptionCode.USERNAME_NOT_VALID,e.getExceptionCode());
        }
    }

    @Test
    public void testCreateUser_Success(){
        when(userPersistenceService.getUserByEmail(any(String.class)))
                .thenReturn(Optional.empty());


        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Cristi");
        userDTO.setLastName("Borcea");
        userDTO.setEmail("dinamo@msggroup.com");
        userDTO.setPhoneNumber("1234456667");
        userDTO.setPassword("IloveSteaua");
        try{
        UserDTO createdUser = userBusinessService.createUser(userDTO);
        assertEquals(userDTO.getFirstName(),createdUser.getFirstName());
        assertEquals(userDTO.getLastName(),createdUser.getLastName());
        assertEquals(userDTO.getEmail(),createdUser.getEmail());
        assertEquals("borcec",createdUser.getUsername());
        } catch (BusinessException e){
            fail("Should not reach this point");
        }
    }

    @Test
    public void testCreateRole_Success(){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setType(RoleEnum.ADMINISTRATOR);

            RoleDTO createdRole = roleBusinessService.createRole(roleDTO);
            assertEquals(createdRole.getType(),roleDTO.getType());
    }

    @Test
    public void testImage(){

    }

}