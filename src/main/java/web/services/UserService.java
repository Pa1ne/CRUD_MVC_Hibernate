package web.services;


import web.models.User;

import java.util.List;


public interface UserService {
    void deleteUser(long id);

    User getUser(long id);

    List<User> getAllUsers();

    void addOrEditUser(User user);
}