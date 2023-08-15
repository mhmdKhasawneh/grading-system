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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/marks")
public class MarksServlet extends HttpServlet {
    private StudentCourseService studentCourseService;

    @Override
    public void init(){
        studentCourseService = (StudentCourseService) getServletContext().getAttribute("studentCourseService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("userName") == null){
            resp.sendRedirect("/login");
            return;
        }
        int studentId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
        List<StudentCourse> studentCourses = new ArrayList<>();
        try {
            studentCourses = studentCourseService.getGrades(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("studentCourses", studentCourses);
        req.getRequestDispatcher("/statistics").forward(req, resp);
    }
}
