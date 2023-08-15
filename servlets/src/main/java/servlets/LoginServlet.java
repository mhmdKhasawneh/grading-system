package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UserService;

import java.io.IOException;
import java.util.Map;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(){
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Map<String, String> data = userService.login(email, password);
        if(data.containsKey("Error")){
            req.setAttribute("Error", data.get("Error"));
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        req.getSession().setAttribute("userId", data.get("user_id"));
        req.getSession().setAttribute("userName", data.get("user_name"));
        req.getSession().setAttribute("userRole", data.get("role_name"));
        req.getSession().setMaxInactiveInterval(1800);
        resp.sendRedirect("/home");
    }
}
