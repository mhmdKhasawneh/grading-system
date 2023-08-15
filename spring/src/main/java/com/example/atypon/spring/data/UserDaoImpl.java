package com.example.atypon.spring.data;

import com.example.atypon.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.example.atypon.spring.data.mappers.UserMapper;

@Component
public class UserDaoImpl implements UserDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void save(User user)  {
        String sql = "INSERT INTO user(name, email, password, role) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getRole().toLowerCase());
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM user WHERE id=?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT id, name, email, password, role FROM user WHERE email=?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), email);
    }
}
