package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;
import services.CourseService;
import services.UserService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addCourse")
public class AddCourseServlet extends HttpServlet {

    private CourseService courseService;
    private UserService userService;
    @Override
    public void init(){
        courseService = (CourseService) getServletContext().getAttribute("courseService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("userName") == null){
            resp.sendRedirect("/login");
            return;
        }
        String courseName = req.getParameter("courseName");
        int courseTeacherId = Integer.parseInt(req.getParameter("courseTeacherId"));
        if(!userService.isIdTeacher(courseTeacherId)){
            req.setAttribute("addCourseError", "Invalid ID or ID does not belong to a teacher.");
            req.getRequestDispatcher("WEB-INF/views/home.jsp").forward(req, resp);
            return;
        }
        try {
            courseService.addCourse(new Course(courseName, courseTeacherId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/home");
    }
}
