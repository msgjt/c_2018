package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserLoginControl {
    @EJB
    private UserManagement userManagement;

    public Boolean validateUser(UserLoginDTO userLoginDTO) {
        try {
            userManagement.login(userLoginDTO.getUserName(), userLoginDTO.getPassword());
            return true;
        } catch (BusinessException e) {
            return false;
        }
    }
}
