package com.example.carserviceapp.config;

import com.example.carserviceapp.model.Role;
import com.example.carserviceapp.model.Status;
import com.example.carserviceapp.model.User;
import com.example.carserviceapp.service.RoleService;
import com.example.carserviceapp.service.StatusService;
import com.example.carserviceapp.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class Datainitializer {
    private final StatusService statusService;
    private final UserService userService;
    private final RoleService roleService;


    public Datainitializer(StatusService statusService,
                           UserService userService,
                           RoleService roleService) {
        this.statusService = statusService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.save(userRole);
        Role masterRole = new Role();
        masterRole.setRoleName(Role.RoleName.MASTER);
        roleService.save(masterRole);
        Role tempRole = new Role();
       tempRole.setRoleName(Role.RoleName.TEMP);
        roleService.save(tempRole);

        User admin = new User();
        admin.setEmail("admin@i.ua");
        admin.setPassword("admin1234");
        admin.setName("Mary Sue");
        admin.setRoles(Set.of(adminRole));
        userService.createUser(admin);

        User johnDoe = new User();
        johnDoe.setEmail("userjohn@i.ua");
        johnDoe.setName("John Doe");
        johnDoe.setPassword("user1234");
        johnDoe.setRoles(Set.of(masterRole));
        userService.createUser(johnDoe);
        User janeDoe = new User();
        janeDoe.setEmail("userjane@i.ua");
        janeDoe.setName("Jane Doe");
        janeDoe.setPassword("user1234");
        janeDoe.setRoles(Set.of(userRole));
        userService.createUser(janeDoe);
        Status projectStatus = new Status(Status.StatusName.PROJECT);
        statusService.createStatus(projectStatus);
        Status acceptedStatus = new Status(Status.StatusName.ACCEPTED);
        statusService.createStatus(acceptedStatus);
        Status toDoStatus = new Status(Status.StatusName.TO_DO);
        statusService.createStatus(toDoStatus);
        Status inProgressStatus = new Status(Status.StatusName.IN_PROGRESS);
        statusService.createStatus(inProgressStatus);
        Status finishedSuccessfulStatus = new Status(Status.StatusName.FINISHED_SUCCESSFUL);
        statusService.createStatus(finishedSuccessfulStatus);
        Status finishedNotSuccessfulStatus = new Status(Status.StatusName.FINISHED_NOT_SUCCESSFUL);
        statusService.createStatus(finishedNotSuccessfulStatus);
        Status paidForStatus = new Status(Status.StatusName.PAID_FOR);
        statusService.createStatus(paidForStatus);
    }
}
