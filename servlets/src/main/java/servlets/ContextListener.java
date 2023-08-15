package servlets;

import data.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import services.*;

import java.sql.SQLException;


@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserService userService = new UserService(new UserDaoImpl());
        CourseStatisticsService courseStatisticsService = new CourseStatisticsService(
                new CourseStatisticsDaoImpl(),
                new StudentCourseDaoImpl()
        );
        CourseService courseService = new CourseService(new CourseDaoImpl(), courseStatisticsService);
        StudentCourseService studentCourseService = new StudentCourseService(new StudentCourseDaoImpl(), courseStatisticsService, courseService);

        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("courseService", courseService);
        sce.getServletContext().setAttribute("studentCourseService", studentCourseService);
        sce.getServletContext().setAttribute("courseStatisticsService", courseStatisticsService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Database.getInstance().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sce.getServletContext().removeAttribute("userService");
        sce.getServletContext().removeAttribute("courseService");
        sce.getServletContext().removeAttribute("studentCourseService");
        sce.getServletContext().removeAttribute("courseStatisticsService");
    }
}
