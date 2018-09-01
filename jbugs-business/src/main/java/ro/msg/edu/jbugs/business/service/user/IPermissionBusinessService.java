package ro.msg.edu.jbugs.business.service.user;

import ro.msg.edu.jbugs.business.dto.user.PermissionDTO;
import java.util.List;


public interface IPermissionBusinessService {

    PermissionDTO getPermissionById(long id);

    List<PermissionDTO> getAllPermissions();

}
