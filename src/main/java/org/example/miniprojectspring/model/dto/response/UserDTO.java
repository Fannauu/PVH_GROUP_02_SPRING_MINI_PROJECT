package org.example.miniprojectspring.model.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private Integer level;
    private Integer xpLevel;
    private String profileImage;
    private Boolean isVerified;
    private LocalDateTime createdAt;
}

