package ro.msg.edu.jbugs.userManagement.business.service;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserSessionDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserLogoutBusinessService {

    @EJB
    private IUserBusinessService IUserBusinessService;

    public boolean logoutUser(UserSessionDTO userSessionDTO) {
        try {
            return IUserBusinessService.logout(userSessionDTO.getUserName());
        } catch (BusinessException e) {
            return false;
        }
    }

}
