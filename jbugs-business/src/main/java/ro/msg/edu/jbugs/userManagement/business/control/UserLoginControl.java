package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDot;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserLoginControl {
    @EJB
    private UserManagement userManagement;

    public Boolean validateUser(UserLoginDot userLoginDot){
        try {
            userManagement.login(userLoginDot.getUserName(), userLoginDot.getPassword());
            return true;
        } catch (BusinessException e) {
            return false;
        }
    }
}
