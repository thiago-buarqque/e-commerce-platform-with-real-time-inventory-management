package com.nozama.app.service;

import com.nozama.app.dto.UserRequest;
import com.nozama.app.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(UserRequest request);
    UserResponse deleteUser(UserRequest request);
    UserResponse getUserById(Long id);
}