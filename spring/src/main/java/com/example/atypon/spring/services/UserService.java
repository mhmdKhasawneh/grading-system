package com.example.atypon.spring.services;

import com.example.atypon.spring.data.UserDao;
import com.example.atypon.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Map<String,String> login(String email, String password){
        Map<String,String> mp = new HashMap<>();
        User user = userDao.findByEmail(email);
        if(user == null){
            mp.put("Error", "Invalid email or password");
        }
        else{
            if(!password.equals(user.getPassword())){
                mp.put("Error", "Invalid email or password");
            }else {
                mp.put("user_id", Integer.toString(user.getId()));
                mp.put("user_name", user.getName());
                mp.put("role_name", user.getRole());
            }
        }
        return mp;
    }

    public void addUser(User user) throws SQLException {
        userDao.save(user);
    }

    public boolean isIdValid(int userId){
        User user = userDao.findById(userId);
        return user != null;
    }

    public boolean isIdTeacher(int userId){
        if(isIdValid(userId)){
            User user = userDao.findById(userId);
            return user.getRole().equalsIgnoreCase("teacher");
        }
        return false;
    }

    public boolean isIdStudent(int userId){
        if(isIdValid(userId)){
            User user = userDao.findById(userId);
            return user.getRole().equalsIgnoreCase("student");
        }
        return false;
    }
}
