package com.nozama.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
}