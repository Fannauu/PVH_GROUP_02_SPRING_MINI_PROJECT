package org.example.miniprojectspring.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Habit {
    private UUID habit_id;
    private String title;
    private String description;
    private String frequency;
    private Boolean isActive;
    private AppUser appuser;
    private LocalDateTime createAt;
//    add new by leab

}
