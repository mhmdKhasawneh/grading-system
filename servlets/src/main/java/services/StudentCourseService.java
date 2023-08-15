package services;

import data.StudentCourseDao;
import models.StudentCourse;

import java.sql.SQLException;
import java.util.List;

public class StudentCourseService {
    private CourseStatisticsService courseStatisticsService;
    private StudentCourseDao studentCourseDao;
    private CourseService courseService;

    public StudentCourseService(StudentCourseDao studentCourseDao, CourseStatisticsService courseStatisticsService, CourseService courseService) {
        this.studentCourseDao = studentCourseDao;
        this.courseStatisticsService = courseStatisticsService;
        this.courseService = courseService;
    }

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
}
