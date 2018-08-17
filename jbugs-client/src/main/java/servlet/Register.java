package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.msg.edu.jbugs.userManagement.business.control.UserManagement;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Register",
        urlPatterns = {"/register"})
public class Register extends HttpServlet {
    @EJB
    private UserManagement userManagement;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        UserDTO userDTO = new UserDTO();
        UserDTO userDTO2 = new UserDTO();
        ObjectMapper mapper = new ObjectMapper();
        userDTO.setLastName(request.getParameter("lastName"));
        userDTO.setFirstName(request.getParameter("firstName"));
        userDTO.setEmail(request.getParameter("email"));
        userDTO.setPhoneNumber(request.getParameter("phoneNumber"));
        userDTO.setPassword(request.getParameter("password"));
        try {
            userDTO2 = userManagement.createUser(userDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        if (userDTO2 != null) {
            out.println(mapper.writeValueAsString(new Boolean(true)));
        } else {
            response.sendError(400);
        }

    }
}
