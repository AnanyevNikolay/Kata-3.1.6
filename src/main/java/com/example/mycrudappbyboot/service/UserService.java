
package com.example.mycrudappbyboot.service;


import com.example.mycrudappbyboot.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void save(User user);
    User getUser(int id);
    void update(int id, User updateUser);
    void delete(int id);
}
