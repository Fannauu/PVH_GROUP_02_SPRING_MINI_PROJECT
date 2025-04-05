package org.example.miniprojectspring.model.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class Habit {
    private UUID habitId;
    private String title;
    private String description;
    private String frequency;
    private Boolean isActive;
    private UUID appUserId;
    private LocalDateTime createAt;
}
