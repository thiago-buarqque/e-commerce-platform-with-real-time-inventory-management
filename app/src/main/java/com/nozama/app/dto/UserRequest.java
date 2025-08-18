package com.nozama.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {
    @NotNull(message = "The id is required")
    private long id;

    @NotNull(message = "The name is required")
    private String name;

    @NotNull(message = "The email is required")
    private String email;

    @NotNull(message = "The password is required")
    private String password;
    private String phone;
}