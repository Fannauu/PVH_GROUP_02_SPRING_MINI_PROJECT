package org.example.miniprojectspring.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.miniprojectspring.model.dto.response.Status;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitLogRequest {
    private LocalDateTime dateTime;
    private Status status;
    private Integer xpEarned;
}
