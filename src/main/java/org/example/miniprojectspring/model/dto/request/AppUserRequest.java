package org.example.miniprojectspring.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppUserRequest {
    @NotBlank(message = "Username cannot be Null!")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please provide a valid email address")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character.")
    private String password;
    private String profileImage;

}