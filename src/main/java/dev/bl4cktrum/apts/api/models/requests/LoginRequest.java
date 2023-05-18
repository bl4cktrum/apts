package dev.bl4cktrum.apts.api.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank()
    @Email(message = "Invalid Email")
    private String email;
    @NotBlank()
    String password;
}
