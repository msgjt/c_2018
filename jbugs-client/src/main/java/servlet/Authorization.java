package servlet;

import jwt.JwtManager;
import ro.msg.edu.jbugs.userManagement.business.control.PermissionManagement;
import ro.msg.edu.jbugs.userManagement.business.control.UserManagement;
import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/authorize"})
public class Authorization extends HttpServlet {

    @EJB
    UserManagement userManagement;

    @EJB
    PermissionManagement permissionManagement;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDTO userDTO=null;
        if (username != null && password != null ) {
//            try {
//                userDTO=userManagement.login(username,password);
//            } catch (BusinessException e) {
//                e.printStackTrace();
//            }
            if ( userDTO != null ) {
                System.out.println("abaaaaaaaaaaaaaaaaaaa");
                response.getWriter().println(
                        "{ \"token\": \""+JwtManager.getInstance().createToken(username)+"\" }");
            } else {
                response.sendError(401);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Logger l = Logger.getLogger("Authorizer");
        Enumeration<String> headerNames = request.getHeaderNames();
        System.out.println("Headers received with the request: ");
        RoleDTO roleDTO = userManagement.getRoleById(1);
        RoleDTO roleDT = userManagement.getRoleById(2);
        PermissionDTO permissionDTO = permissionManagement.getPermissionById(1);
        try {
            out.println(roleDTO.getType());
            out.println(permissionDTO.getType());
            permissionManagement.addPermissionForRole(roleDTO,permissionDTO);
            permissionManagement.addPermissionForRole(roleDT,permissionDTO);
            //permissionManagement.deletePermissionForRole(roleDTO,permissionDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while( headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            System.out.println(headerName+": " +request.getHeader(headerName));
        }
        out.println("Everything OK!");

    }

}




