package server.services;
import server.data.UserDao;
import server.models.Role;
import server.models.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

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
                mp.put("role_name", user.getRole().toString());
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
            return user.getRole().equals(Role.TEACHER);
        }
        return false;
    }

    public boolean isIdStudent(int userId){
        if(isIdValid(userId)){
            User user = userDao.findById(userId);
            return user.getRole().equals(Role.STUDENT);
        }
        return false;
    }
}
