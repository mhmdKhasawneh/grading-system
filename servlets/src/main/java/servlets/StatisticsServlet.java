package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.StudentCourse;
import models.CourseStatistics;
import services.CourseStatisticsService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {
    private CourseStatisticsService courseStatisticsService;

    @Override
    public void init(){
        courseStatisticsService = (CourseStatisticsService) getServletContext().getAttribute("courseStatisticsService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentCourse> studentCourses = (List<StudentCourse>) req.getAttribute("studentCourses");
        List<CourseStatistics> courseStatistics = new ArrayList<>();
        for(StudentCourse studentCourse : studentCourses){
            try {
                CourseStatistics stat = courseStatisticsService.getCourseStatistics(studentCourse.getCourseName());
                courseStatistics.add(stat);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        req.setAttribute("stats", courseStatistics);
        req.getRequestDispatcher("WEB-INF/views/home.jsp").forward(req, resp);
    }
}
