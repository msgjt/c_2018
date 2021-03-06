package ro.msg.edu.jbugs.userManagement.business.service;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.RoleDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.user.PermissionDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.service.user.RoleBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.RoleEnum;
import ro.msg.edu.jbugs.userManagement.persistence.exceptions.PersistenceException;
import ro.msg.edu.jbugs.userManagement.persistence.service.RolePersistenceService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RolePersistenceServiceBeanTest {

    @InjectMocks
    private RoleBusinessService roleBusinessService;

    @Mock
    private RoleDTOHelper roleDTOHelper;

    @Mock
    private RolePersistenceService rolePersistenceService;

    @Test
    public void createRole_ExpectedOK(){
        Role role=new Role();
        RoleDTO roleDTO=new RoleDTO();
        roleDTO.setType(RoleEnum.TEST_MANAGER);
        when(roleDTOHelper.toEntity(roleDTO)).thenReturn(role);
        when(rolePersistenceService.createRole(role)).thenReturn(Optional.of(role));
        when(roleDTOHelper.fromEntity(role)).thenReturn(roleDTO);
        assertEquals(roleDTO.getType(), roleBusinessService.createRole(roleDTO).getType());
    }

    @Test
    public void updateRole_ExpectedOK(){
        Role role=new Role();
        RoleDTO roleDTO=new RoleDTO();
        roleDTO.setId(3L);
        roleDTO.setType(RoleEnum.DEVELOPER);
        when(roleDTOHelper.toEntity(roleDTO)).thenReturn(role);
        when(rolePersistenceService.createRole(role)).thenReturn(Optional.of(role));
        when(roleDTOHelper.fromEntity(role)).thenReturn(roleDTO);
        RoleDTO updateRole=null;
        try{
            updateRole=roleBusinessService.updateRole(roleDTO);
        }catch (PersistenceException e){
            e.printStackTrace();
        }
        assertEquals(roleDTO.getType(),updateRole.getType());
    }

    @Test
    public void getRoleById_ExpectedOK(){
        Role role=new Role();
        RoleDTO roleDTO=new RoleDTO();

        try{
            when(rolePersistenceService.getRoleById(1)).thenReturn(Optional.of(role));
            when(roleDTOHelper.fromEntity(role)).thenReturn(roleDTO);
            assertEquals(roleDTO.getId(),roleBusinessService.getRoleById(1
            ).getId());
        }catch(PersistenceException e){
            e.printStackTrace();
        }
    }



}
