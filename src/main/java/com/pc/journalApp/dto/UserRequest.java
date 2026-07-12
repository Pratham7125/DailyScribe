package com.pc.journalApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "Request body for creating a new user")
@Data
public class UserRequest {

    @Schema(example = "pratham")
    @NotBlank
    private String userName;

    @Schema(example = "Password@123")
    @NotBlank
    private String password;

    @Schema(example = "pratham@gmail.com", nullable = true)
    @Email
    private String email;

}