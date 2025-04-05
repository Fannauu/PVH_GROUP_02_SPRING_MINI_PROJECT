package org.example.miniprojectspring.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AchievementRequest {
    private String title;
    private String description;
    private String badge;
    private Integer xpRequired;
}
