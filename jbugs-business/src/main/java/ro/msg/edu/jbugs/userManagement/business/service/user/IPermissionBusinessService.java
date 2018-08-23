package ro.msg.edu.jbugs.userManagement.business.service.user;

import ro.msg.edu.jbugs.userManagement.business.dto.user.PermissionDTO;
import java.util.List;


public interface IPermissionBusinessService {

    PermissionDTO getPermissionById(long id);

    List<PermissionDTO> getAllPermissions();

}
