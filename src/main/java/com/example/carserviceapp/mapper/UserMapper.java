package com.example.carserviceapp.mapper;

import com.example.carserviceapp.dto.request.UserRequestDto;
import com.example.carserviceapp.dto.response.UserResponseDto;
import com.example.carserviceapp.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RequestMapper<UserRequestDto, User>,
        ResponseMapper<User, UserResponseDto> {
    @Override
    public User mapToModel(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    @Override
    public UserResponseDto mapToDto(User model) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(model.getId());
        userResponseDto.setName(model.getName());
        userResponseDto.setEmail(model.getEmail());
        return userResponseDto;
    }
}
