package org.example.miniprojectspring.model.dto.request;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.miniprojectspring.model.dto.response.Frequency;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class HabitRequest {
    private String title;
    private String description;
    private Frequency frequency;

}
