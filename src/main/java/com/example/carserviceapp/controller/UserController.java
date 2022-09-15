package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.request.UserRequestDto;
import com.example.carserviceapp.dto.response.MasterResponseDto;
import com.example.carserviceapp.dto.response.UserResponseDto;
import com.example.carserviceapp.mapper.RequestMapper;
import com.example.carserviceapp.mapper.ResponseMapper;
import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.model.User;
import com.example.carserviceapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RequestMapper<UserRequestDto, User> userRequestMapper;
    private final ResponseMapper<User, UserResponseDto> userResponseMapper;
    private final ResponseMapper<Master, MasterResponseDto> masterResponseMapper;

    public UserController(UserService userService,
                          RequestMapper<UserRequestDto, User> userRequestMapper,
                          ResponseMapper<User, UserResponseDto> userResponseMapper,
                          ResponseMapper<Master, MasterResponseDto> masterResponseMapper) {
        this.userService = userService;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
        this.masterResponseMapper = masterResponseMapper;
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userResponseMapper.mapToDto(userService.createUser(
              userRequestMapper.mapToModel(userRequestDto)));
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userResponseMapper.mapToDto(userService.getUserById(id));
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userResponseMapper:: mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUserById(@PathVariable Long id,
                                          @RequestBody UserRequestDto userRequestDto) {
        return userResponseMapper.mapToDto(userService.updateUserById(id,
                userRequestMapper.mapToModel(userRequestDto)));
    }

    @DeleteMapping("/{id}")
    public UserResponseDto deleteUserById(@PathVariable Long id) {
        return userResponseMapper.mapToDto(userService.deleteUserById(id));
    }

    @PutMapping("/{id}/approve")
    public MasterResponseDto approveUserAsMAster(@PathVariable Long id) {
        return masterResponseMapper.mapToDto(userService.approveUserAsMaster(id));
    }
}
