package services;

import data.CourseDao;
import models.Course;
import models.CourseStatistics;

import java.sql.SQLException;

public class CourseService {
    private final CourseDao courseDao;
    private final CourseStatisticsService courseStatisticsService;
    public CourseService(CourseDao courseDao, CourseStatisticsService courseStatisticsService) {
        this.courseDao = courseDao;
        this.courseStatisticsService = courseStatisticsService;
    }

    public void addCourse(Course course) throws SQLException {
        courseDao.save(course);
        courseStatisticsService.add(new CourseStatistics(course.getName(), 0, 0, 0));
    }

    public Course getCourse(String courseName){
        Course course = courseDao.findByName(courseName);
        return course;
    }
}
