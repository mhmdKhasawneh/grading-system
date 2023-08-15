package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.UserService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(){
        userService = (UserService) getServletContext().getAttribute("userService");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getSession().getAttribute("userName") == null){
            resp.sendRedirect("/login");
            return;
        }
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(req.getParameter("student") != null){
            try {
                userService.addUser(new User(-1, name, email, password, Role.STUDENT));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(req.getParameter("teacher") != null){
            try {
                userService.addUser(new User(-1, name, email, password, Role.TEACHER));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/home");
    }
}
