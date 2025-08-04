package com.nozama.app.controller;

import com.nozama.app.dto.UserRequest;
import com.nozama.app.dto.UserResponse;
import com.nozama.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserResponse updateUser(@Valid @RequestBody UserRequest request) {
        return userService.updateUser(request);
    }
    @DeleteMapping("/deleteUser")
    public UserResponse deleteUser(@Valid @RequestBody UserRequest request) {
        return userService.deleteUser(request);
    }
}