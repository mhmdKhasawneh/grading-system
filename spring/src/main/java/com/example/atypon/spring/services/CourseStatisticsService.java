package com.example.atypon.spring.services;


import com.example.atypon.spring.data.CourseStatisticsDao;
import com.example.atypon.spring.data.StudentCourseDao;
import com.example.atypon.spring.models.CourseStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class CourseStatisticsService {
    @Autowired
    private CourseStatisticsDao courseStatisticsDao;
    @Autowired
    private StudentCourseDao studentCourseDao;


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
