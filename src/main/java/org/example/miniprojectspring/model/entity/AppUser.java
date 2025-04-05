package org.example.miniprojectspring.model.entity;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Integer level;
    private Integer xp;
    private String profileImage;
    private Boolean isVerified;
    private LocalDateTime createAt;
}
