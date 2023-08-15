package com.example.atypon.spring.services;

import com.example.atypon.spring.data.CourseDao;
import com.example.atypon.spring.models.Course;
import com.example.atypon.spring.models.CourseStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private  CourseStatisticsService courseStatisticsService;

    public void addCourse(Course course) throws SQLException {
        courseDao.save(course);
        courseStatisticsService.add(new CourseStatistics(course.getName(), 0, 0, 0));
    }

    public Course getCourse(String courseName){
        Course course = courseDao.findByName(courseName);
        return course;
    }
}
