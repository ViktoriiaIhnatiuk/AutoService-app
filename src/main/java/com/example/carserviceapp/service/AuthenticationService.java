package com.example.carserviceapp.service;

import com.example.carserviceapp.exception.AuthenticationException;
import com.example.carserviceapp.model.User;

public interface AuthenticationService {
    User register(String username, String email, String password, boolean isMaster);

    User login(String email, String password) throws AuthenticationException;
}
