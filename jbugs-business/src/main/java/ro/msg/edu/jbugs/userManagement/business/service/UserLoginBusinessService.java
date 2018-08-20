package ro.msg.edu.jbugs.userManagement.business.service;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserLoginBusinessService {
    @EJB
    private IUserBusinessService IUserBusinessService;

    public Boolean validateUser(UserLoginDTO userLoginDTO) {
        try {
            IUserBusinessService.login(userLoginDTO.getUserName(), userLoginDTO.getPassword());
            return true;
        } catch (BusinessException e) {
            return false;
        }
    }
}
