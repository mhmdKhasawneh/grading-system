package server.services;

import server.data.CourseStatisticsDao;
import server.data.StudentCourseDao;
import server.models.CourseStatistics;

import java.sql.SQLException;

public class CourseStatisticsService {
    private CourseStatisticsDao courseStatisticsDao;
    private StudentCourseDao studentCourseDao;

    public CourseStatisticsService(CourseStatisticsDao courseStatisticsDao, StudentCourseDao studentCourseDao){
        this.courseStatisticsDao = courseStatisticsDao;
        this.studentCourseDao = studentCourseDao;
    }

    public void add(CourseStatistics courseStatistics) throws SQLException {
        courseStatisticsDao.save(courseStatistics);
    }

    public void updateStatistics(String courseName) throws SQLException {
        int newMax = studentCourseDao.findMaxMarkFor(courseName);
        int newMin = studentCourseDao.findMinMarkFor(courseName);
        int newAvg = studentCourseDao.findAvgMarkFor(courseName);

        courseStatisticsDao.updateCourseStatistics(new CourseStatistics(courseName, newMax, newMin, newAvg));
    }
    public CourseStatistics getCourseStatistics(String courseName) throws SQLException {
        CourseStatistics courseStatistics = courseStatisticsDao.findByCourseName(courseName);
        return courseStatistics;
    }
}
