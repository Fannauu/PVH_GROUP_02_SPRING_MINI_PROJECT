package org.example.miniprojectspring.model.dto.request;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class HabitRequest {
    private String title;
    private String description;
    private String frequency;

}
