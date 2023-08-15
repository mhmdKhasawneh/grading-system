package com.example.atypon.spring.data;


import com.example.atypon.spring.models.CourseStatistics;

public interface CourseStatisticsDao {
    void save(CourseStatistics courseStatistics);
    CourseStatistics findByCourseName(String courseName);
    void updateCourseStatistics(CourseStatistics courseStatistics);
}
