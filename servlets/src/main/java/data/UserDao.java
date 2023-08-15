package data;

import models.User;

public interface UserDao {
    void save(User user);
    User findById(int id);
    User findByEmail(String email);
}
