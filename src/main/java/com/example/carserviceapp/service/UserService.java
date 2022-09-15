package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long userId);

    User updateUserById(Long userId, User user);

    User deleteUserById(Long userId);

    User getUserByEmail(String email);

    String getUserEmail();

    Boolean hasAdminRole(User user);

    User getCurrentAuthenticatedUser();

    Master approveUserAsMaster(Long userId);
}
