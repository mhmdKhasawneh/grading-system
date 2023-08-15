package com.example.atypon.spring.data;


import com.example.atypon.spring.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.example.atypon.spring.data.mappers.CourseMapper;

@Component
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Course course) {
        String sql = "INSERT INTO course(name, teacher_id) VALUES(?, ?)";
        jdbcTemplate.update(sql, course.getName(), course.getTeacherId());
    }

    @Override
    public Course findByName(String courseName) {
        String sql = "SELECT * from course WHERE name=?";
        Course course = null;
        try {
            course = jdbcTemplate.queryForObject(sql, new CourseMapper(), courseName);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return null;
        }
        return course;
    }
}
