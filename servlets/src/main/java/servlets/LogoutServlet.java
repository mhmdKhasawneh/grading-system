package servlets;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        if(req.getSession().getAttribute("userName") == null){
            res.sendRedirect("/login");
            return;
        }
        req.getSession().invalidate();
        res.sendRedirect("/home");
    }


}