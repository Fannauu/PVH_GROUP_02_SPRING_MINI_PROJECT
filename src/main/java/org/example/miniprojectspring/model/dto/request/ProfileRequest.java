package org.example.miniprojectspring.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequest {
    @NotBlank(message = "Username cannot be Blank!")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String name;
    private String profileImage;
}
