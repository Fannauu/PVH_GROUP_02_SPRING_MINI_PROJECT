package org.example.miniprojectspring.model.dto.request;


import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Title can't blank")
    private String title;
    @NotBlank(message = "Title can't blank")
    private String description;
    @NotBlank(message = "Title can't blank")
    private Frequency frequency;
}
