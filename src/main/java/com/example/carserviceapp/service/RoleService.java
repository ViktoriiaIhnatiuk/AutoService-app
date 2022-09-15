package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Role;

public interface RoleService {
    Role save(Role role);

    Role findAllByRoleName(String roleName);
}
