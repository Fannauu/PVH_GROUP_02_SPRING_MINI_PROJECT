package org.example.miniprojectspring.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class Profile {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Integer level;
    private Integer xpLevel;
    private String profileImage;
    private Boolean isVerified;
    private LocalDateTime createdAt;
}
