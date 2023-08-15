package com.example.atypon.spring.data.mappers;

import com.example.atypon.spring.models.StudentCourse;
import com.example.atypon.spring.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentCourseMapper implements RowMapper<StudentCourse> {
    @Override
    public StudentCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new StudentCourse(rs.getInt(1), rs.getString(2), rs.getInt(3));
    }
}
