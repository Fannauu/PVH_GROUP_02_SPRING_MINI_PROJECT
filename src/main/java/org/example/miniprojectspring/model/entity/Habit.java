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
@Builder
public class Habit {
    private UUID id;
    private String title;
    private String description;
    private enum frequency{DAILY,WEEKLY,MONTHLY};
    private Boolean isActive;
    private LocalDateTime createAt;
    private AppUser appUser;


}
