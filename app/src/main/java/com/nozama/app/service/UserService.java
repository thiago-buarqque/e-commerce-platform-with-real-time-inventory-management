package com.nozama.app.service;

import com.nozama.app.dto.UserRequest;
import com.nozama.app.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(UserRequest request);
    UserResponse deleteUser(UserRequest request);
    UserResponse getUserById(Long id);
    Page<UserResponse> searchUsers(
            String search,
            String role,
            Pageable pageable
    );
}