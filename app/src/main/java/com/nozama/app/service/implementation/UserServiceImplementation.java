package com.nozama.app.service.implementation;

import com.nozama.app.dto.UserRequest;
import com.nozama.app.dto.UserResponse;
import com.nozama.app.model.User;
import com.nozama.app.model.UserRole;
import com.nozama.app.repository.UserRepository;
import com.nozama.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build();

        User saved = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());

        return response;
    }

    @Override
    public UserResponse updateUser(UserRequest request) {
     User user = userRepository.findById(request.getId())
             .orElseThrow(() -> new RuntimeException("User with id " + request.getId() + " not found"));

     user.setName(request.getName());
     user.setEmail(request.getEmail());
     if (request.getPassword() != null && !request.getPassword().isBlank()) {
        user.setPassword(passwordEncoder.encode(request.getPassword()));
     }

     User updatedUser = userRepository.save(user);

     UserResponse response = new UserResponse();
     response.setId(updatedUser.getId());
     response.setName(updatedUser.getName());
     response.setEmail(updatedUser.getEmail());

     return response;
    }

    @Override
    public UserResponse deleteUser(UserRequest request) {
        Long userId = request.getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id: " + userId + " not found"));

        userRepository.delete(user);

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        return response;
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        return response;
    }
}