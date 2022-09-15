package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.model.Role;
import com.example.carserviceapp.model.User;
import com.example.carserviceapp.repository.UserRepository;
import com.example.carserviceapp.security.CustomUserDetails;
import com.example.carserviceapp.service.MasterService;
import com.example.carserviceapp.service.RoleService;
import com.example.carserviceapp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final MasterService masterService;

    public UserServiceImpl(PasswordEncoder encoder,
                           UserRepository userRepository,
                           RoleService roleService,
                           MasterService masterService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.masterService = masterService;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Can't find user by id " + userId));
    }

    @Override
    public User updateUserById(Long userId, User user) {
        User userToUpdate = getUserById(userId);
        if (user.getName() != null) {
            userToUpdate.setName(user.getName());
        }
        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
        }
        return createUser(userToUpdate);
    }

    @Override
    public User deleteUserById(Long userId) {
        User userToDelete = getUserById(userId);
        userRepository.delete(userToDelete);
        return userToDelete;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new RuntimeException("Can`t find user by email " + email));
    }

    @Override
    public String getUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getEmail();
    }

    @Override
    public Boolean hasAdminRole(User user) {
        return user.getRoles().stream()
                .map(Role::getRoleName).anyMatch(e -> e.equals(Role.RoleName.ADMIN));
    }

    @Override
    public User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return getUserByEmail(userDetails.getEmail());
    }

    @Override
    public Master approveUserAsMaster(Long userId) {
        User userToApprove = getUserById(userId);
        Set<Role> roles = userToApprove.getRoles();
        Role tempRole = roleService.findAllByRoleName(Role.RoleName.TEMP.name());
        Role masterRole = roleService.findAllByRoleName(Role.RoleName.MASTER.name());
        if (roles.contains(tempRole)) {
            roles.remove(tempRole);
            roles.add(masterRole);
        }
        Master master = new Master();
        master.setName(userToApprove.getName());
        master.setUserId(userToApprove.getId());
        return masterService.createMaster(master);
    }
}
