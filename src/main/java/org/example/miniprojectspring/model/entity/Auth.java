package org.example.miniprojectspring.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class Auth {
    private UUID appUserId;
    private String userName;
    private String email;
    private String username;
    private Integer level;
    private Integer xp;
    private String profileImageUrl;
    private boolean isVerified;
    private LocalDateTime createdAt;
}
