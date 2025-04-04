package org.example.miniprojectspring.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class HabitRequest {
    private String title;
    private String description;
    private String frequency;
    private Boolean isActive;
    private LocalDateTime createAt;
}
