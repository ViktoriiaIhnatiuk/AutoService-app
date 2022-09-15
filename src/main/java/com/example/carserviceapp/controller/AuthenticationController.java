package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.request.RegisterRequestDto;
import com.example.carserviceapp.dto.request.UserRequestDto;
import com.example.carserviceapp.dto.response.UserResponseDto;
import com.example.carserviceapp.exception.AuthenticationException;
import com.example.carserviceapp.mapper.ResponseMapper;
import com.example.carserviceapp.model.User;
import com.example.carserviceapp.security.jwt.JwtTokenProvider;
import com.example.carserviceapp.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final ResponseMapper<User, UserResponseDto> userResponseDtoMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationService authService,
                                    ResponseMapper<User, UserResponseDto> userResponseDtoMapper,
                                    JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.userResponseDtoMapper = userResponseDtoMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid RegisterRequestDto requestDto) {
        User user = authService.register(requestDto.getName(), requestDto.getEmail(),
                requestDto.getPassword(), requestDto.isMaster());
        return userResponseDtoMapper.mapToDto(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserRequestDto requestDto)
            throws AuthenticationException {
        User user = authService.login(requestDto.getEmail(), requestDto.getPassword());
        String token = jwtTokenProvider.createToken(user.getEmail(),
                user.getRoles().stream()
                .map(e -> e.getRoleName().name())
                .collect(Collectors.toList()));
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}
