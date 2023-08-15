package com.example.atypon.spring.data;


import com.example.atypon.spring.models.Course;

public interface CourseDao {
    void save(Course course);
    Course findByName(String courseName);
}
