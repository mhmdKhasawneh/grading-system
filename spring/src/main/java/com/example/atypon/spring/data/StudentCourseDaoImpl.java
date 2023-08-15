package com.example.atypon.spring.data;


import com.example.atypon.spring.models.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.example.atypon.spring.data.mappers.StudentCourseMapper;

import java.util.List;

@Component
public class StudentCourseDaoImpl implements StudentCourseDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void save(StudentCourse studentCourse) {
        String sql = "INSERT INTO student_course(student_id, course_name, mark) VALUES(?, ?, 0)";
        jdbcTemplate.update(sql, studentCourse.getStudentId(), studentCourse.getCourseName());
    }

    @Override
    public void updateStudentCourse(StudentCourse studentCourse) {
        String sql = "UPDATE student_course SET mark=? WHERE student_id=? AND course_name=?";
        jdbcTemplate.update(sql, studentCourse.getMark(), studentCourse.getStudentId(), studentCourse.getCourseName());
    }

    @Override
    public List<StudentCourse> getGradesFor(int studentId) {
        String sql = "SELECT student_id, course_name, mark FROM student_course WHERE student_id=?";
        return jdbcTemplate.query(sql, new StudentCourseMapper(), studentId);
    }

    @Override
    public List<StudentCourse> findByStudentId(int studentId) {
        String sql = "SELECT student_id, course_name, mark FROM student_course WHERE student_id=?";
        return jdbcTemplate.query(sql, new StudentCourseMapper(), studentId);
    }

    @Override
    public int findMaxMarkFor(String courseName) {
        String sql = "SELECT MAX(mark) AS max_mark FROM student_course WHERE course_name=?";
        Integer max = jdbcTemplate.queryForObject(sql, Integer.class, courseName);
        return (max != null ? max : -1);
    }

    @Override
    public int findMinMarkFor(String courseName) {
        String sql = "SELECT MIN(mark) AS min_mark FROM student_course WHERE course_name=?";
        Integer min = jdbcTemplate.queryForObject(sql, Integer.class, courseName);
        return (min != null ? min : -1);
    }

    @Override
    public int findAvgMarkFor(String courseName) {
        String sql = "SELECT AVG(mark) AS avg_mark FROM student_course WHERE course_name=?";
        Integer avg = jdbcTemplate.queryForObject(sql, Integer.class, courseName);
        return (avg != null ? avg : -1);
    }
}
