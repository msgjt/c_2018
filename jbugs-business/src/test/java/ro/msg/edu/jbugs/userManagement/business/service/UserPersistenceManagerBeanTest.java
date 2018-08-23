package ro.msg.edu.jbugs.userManagement.business.service;

import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.business.service.user.UserBusinessService;
import ro.msg.edu.jbugs.userManagement.business.service.user.UserLoginBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.RoleEnum;
import ro.msg.edu.jbugs.userManagement.persistence.service.UserPersistenceService;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserPersistenceManagerBeanTest {

    @InjectMocks
    private UserBusinessService userBusinessService;

    @InjectMocks
    private UserLoginBusinessService loginBusinessService;


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

        when(userPersistenceService.getUsernamesLike(any(String.class))).thenReturn(new ArrayList<>());
        String suffix = userBusinessService.createSuffix("dorel0");
        assertEquals( "",suffix);

    }

    @Test
    public void createSuffix_expected4(){


        when(userPersistenceService.getUsernamesLike(any(String.class)))
                .thenReturn(
                        new ArrayList<String>(){{
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
                        new ArrayList<String>(){{
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
                        new ArrayList<String>(){{
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
        try{
            RoleDTO createdRole = userBusinessService.createRole(roleDTO);
            assertEquals(createdRole.getType(),roleDTO.getType());
        }catch(BusinessException e){
            e.printStackTrace();
        }
    }

}