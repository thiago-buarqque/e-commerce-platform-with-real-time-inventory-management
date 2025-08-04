package com.nozama.app.controller;

import com.nozama.app.dto.UserRequest;
import com.nozama.app.dto.UserResponse;
import com.nozama.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse registerUser(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }
}