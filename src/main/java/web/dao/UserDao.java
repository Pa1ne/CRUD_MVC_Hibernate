package web.dao;


import web.models.User;

import java.util.List;


public interface UserDao {
    List<User> getAllUsers();

    void addOrEditUser(User user);

    User getUser(long id);

    void deleteUser(long id);
}
