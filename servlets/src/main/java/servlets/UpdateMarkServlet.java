package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.StudentCourse;
import services.StudentCourseService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateMark")
public class UpdateMarkServlet extends HttpServlet {
    private StudentCourseService studentCourseService;

    @Override
    public void init(){
        studentCourseService = (StudentCourseService) getServletContext().getAttribute("studentCourseService");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getSession().getAttribute("userName") == null){
            resp.sendRedirect("/login");
            return;
        }
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        String courseName = req.getParameter("courseName");
        int mark = Integer.parseInt(req.getParameter("mark"));
        if(mark<0 || mark>100){
            req.setAttribute("updateMarkError", "Please enter a valid mark");
            req.getRequestDispatcher("WEB-INF/views/home.jsp").forward(req, resp);
            return;
        }
        try {
            studentCourseService.updateMark(new StudentCourse(studentId, courseName, mark));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/home");
    }
}
