package com.example.atypon.spring.data;

import com.example.atypon.spring.models.User;

public interface UserDao {
    void save(User user);
    User findById(int id);
    User findByEmail(String email);
}
