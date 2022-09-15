package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.exception.AuthenticationException;
import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.model.Role;
import com.example.carserviceapp.model.User;
import com.example.carserviceapp.repository.UserRepository;
import com.example.carserviceapp.service.AuthenticationService;
import com.example.carserviceapp.service.CarOwnerService;
import com.example.carserviceapp.service.RoleService;
import com.example.carserviceapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CarOwnerService carOwnerService;

    public AuthenticationServiceImpl(UserService userService,
                                     RoleService roleService,
                                     PasswordEncoder passwordEncoder,
                                     UserRepository userRepository,
                                     CarOwnerService carOwnerService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.carOwnerService = carOwnerService;
    }

    @Override
    public User register(String username, String email, String password, boolean isMaster) {
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword(password);
        User createdUser = new User();
        if (isMaster == true) {
            user.setRoles(Set.of(roleService.findAllByRoleName(Role.RoleName.USER.name()),
                    roleService.findAllByRoleName(Role.RoleName.TEMP.name())));
            createdUser = userService.createUser(user);
        } else {
            user.setRoles(Set.of(roleService.findAllByRoleName(Role.RoleName.USER.name())));
            createdUser = userService.createUser(user);
            CarOwner carOwner = new CarOwner();
            carOwner.setName(createdUser.getName());
            carOwner.setUserId(createdUser.getId());
            carOwnerService.createCarOwner(carOwner);
        }
        return createdUser;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Incorrect username or password!"));

        if (user.getEmail().equals(email)
                && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new AuthenticationException("Incorrect username or password!");
        }
    }
}
