package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Role;

import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getSession().getAttribute("userName") == null){
            resp.sendRedirect("/login");
            return;
        }
        if(req.getSession().getAttribute("userRole").equals(Role.STUDENT.toString())){
            System.out.println("Student");
            req.getRequestDispatcher("/marks").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("WEB-INF/views/home.jsp").forward(req, resp);
    }
}
