package server.initializers;

import server.data.CourseDaoImpl;
import server.data.CourseStatisticsDaoImpl;
import server.data.StudentCourseDaoImpl;
import server.data.UserDaoImpl;
import server.services.CourseService;
import server.services.CourseStatisticsService;
import server.services.StudentCourseService;
import server.services.UserService;

public class ServicesInit {
    private static UserService userService;
    private static CourseService courseService;
    private static StudentCourseService studentCourseService;
    private static CourseStatisticsService courseStatisticsService;
    public static void init(){
        userService = new UserService(new UserDaoImpl());
        courseStatisticsService = new CourseStatisticsService(new CourseStatisticsDaoImpl(), new StudentCourseDaoImpl());
        courseService = new CourseService(new CourseDaoImpl(), courseStatisticsService);
        studentCourseService = new StudentCourseService(new StudentCourseDaoImpl(), courseService);
    }

    public static UserService getUserService() {
        return userService;
    }

    public static CourseService getCourseService() {
        return courseService;
    }

    public static StudentCourseService getStudentCourseService() {
        return studentCourseService;
    }

    public static CourseStatisticsService getCourseStatisticsService() {
        return courseStatisticsService;
    }
}
