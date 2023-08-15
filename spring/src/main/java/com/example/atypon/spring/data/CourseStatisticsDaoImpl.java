package com.example.atypon.spring.data;


import com.example.atypon.spring.models.CourseStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.example.atypon.spring.data.mappers.CourseStatisticsMapper;

@Component
public class CourseStatisticsDaoImpl implements CourseStatisticsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(CourseStatistics courseStatistics) {
        String sql = "INSERT INTO course_statistics(course_name) VALUES(?)";
        jdbcTemplate.update(sql, courseStatistics.getCourseName());
    }

    @Override
    public CourseStatistics findByCourseName(String courseName) {
        String sql = "SELECT course_name, max, min, average FROM course_statistics WHERE course_name=?";
        return jdbcTemplate.queryForObject(sql, new CourseStatisticsMapper(), courseName);
    }

    @Override
    public void updateCourseStatistics(CourseStatistics courseStatistics) {
        String sql = "UPDATE course_statistics SET max=?, min=?, average=? WHERE course_name=?";
        jdbcTemplate.update(sql,
                courseStatistics.getMax(),
                courseStatistics.getMin(),
                courseStatistics.getAvg(),
                courseStatistics.getCourseName()
        );
    }
}
