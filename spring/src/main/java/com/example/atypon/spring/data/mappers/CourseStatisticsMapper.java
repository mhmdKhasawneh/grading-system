package com.example.atypon.spring.data.mappers;

import com.example.atypon.spring.models.CourseStatistics;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStatisticsMapper implements RowMapper<CourseStatistics> {
    @Override
    public CourseStatistics mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CourseStatistics(rs.getString(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getInt(4)
        );
    }
}
