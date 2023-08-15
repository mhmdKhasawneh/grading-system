package com.example.atypon.spring.services;


import com.example.atypon.spring.data.StudentCourseDao;
import com.example.atypon.spring.models.StudentCourse;
import com.example.atypon.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentCourseService {
    @Autowired
    private CourseStatisticsService courseStatisticsService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentCourseDao studentCourseDao;
    @Autowired
    private CourseService courseService;

    public List<StudentCourse> getGrades(int studentId) throws SQLException {
        List<StudentCourse> list = studentCourseDao.getGradesFor(studentId);
        return list;
    }

    public void enroll(StudentCourse studentCourse) throws SQLException {
        if(courseService.getCourse(studentCourse.getCourseName()) == null){
            throw new IllegalArgumentException("Course name does not exist");
        }
        studentCourseDao.save(studentCourse);
    }

    public void updateMark(StudentCourse studentCourse) throws SQLException {
        studentCourseDao.updateStudentCourse(studentCourse);

        courseStatisticsService.updateStatistics(studentCourse.getCourseName());
    }

    public boolean isEnrolled(int studentId, String courseName){
        if(!userService.isIdStudent(studentId)){
            return false;
        }
        List<StudentCourse> studentCourses = studentCourseDao.findByStudentId(studentId);
        return !studentCourses.isEmpty();
    }
}
