package org.example.miniprojectspring.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAppRequest {
    private String name;
    private String email;
    private String password;
    private Integer level;
    private Integer xpLevel;
    private String profileImage;
    private Boolean isVerified;
    private LocalDateTime createdAt;
}
