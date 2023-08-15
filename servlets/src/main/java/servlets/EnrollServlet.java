package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.StudentCourse;
import services.CourseService;
import services.StudentCourseService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/enroll")
public class EnrollServlet extends HttpServlet {

    private StudentCourseService studentCourseService;

    @Override
    public void init(){
        studentCourseService = (StudentCourseService) getServletContext().getAttribute("studentCourseService");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("userName") == null){
            resp.sendRedirect("/login");
            return;
        }
        String courseName = req.getParameter("courseName");
        int studentId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
        try{
            studentCourseService.enroll(new StudentCourse(studentId, courseName, 0));
        } catch(SQLException e){
            e.printStackTrace();
        }
        resp.sendRedirect("/");
    }
}
