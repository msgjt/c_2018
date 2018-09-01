package ro.msg.edu.jbugs.business.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ro.msg.edu.jbugs.business.dto.helper.PermissionDTOHelper;
import ro.msg.edu.jbugs.business.dto.user.PermissionDTO;
import ro.msg.edu.jbugs.business.service.user.PermissionBusinessService;
import ro.msg.edu.jbugs.persistence.entity.Permission;
import ro.msg.edu.jbugs.persistence.service.user.PermissionPersistenceService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PermissionPersistenceServiceBeanTest {

    @InjectMocks
    private PermissionBusinessService permissionBusinessService;

    @Mock
    private PermissionDTOHelper permissionDTOHelper;

    @Mock
    private PermissionPersistenceService permissionPersistenceService;

    private List<Permission> permissions;
    @Before
    public void initialize() {
        permissions = new ArrayList<>();
    }

    @Test
    public void getPermissionById_ExpectedOk(){
        Permission permission = new Permission();
        PermissionDTO permissionDTO = new PermissionDTO();
        when(permissionPersistenceService.getPermissionForId(1)).thenReturn(Optional.of(permission));
        when(permissionDTOHelper.fromEntity(permission)).thenReturn(permissionDTO);
        assertEquals(permissionDTO.getId(),permissionBusinessService.getPermissionById(1).getId());
    }

    @Test
    public void getAllPermissions_ExpectedOK(){
        Permission permission = new Permission();
        when(permissionPersistenceService.getAllPermissions())
                .thenReturn(Arrays.asList(permission));
        assertEquals(1,permissionBusinessService.getAllPermissions().size());
    }
}
