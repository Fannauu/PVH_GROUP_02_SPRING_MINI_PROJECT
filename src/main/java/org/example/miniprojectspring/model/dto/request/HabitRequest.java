package org.example.miniprojectspring.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.example.miniprojectspring.model.dto.response.Frequency;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitRequest {
    private String title;
    private String description;
    private Frequency frequency;
}
