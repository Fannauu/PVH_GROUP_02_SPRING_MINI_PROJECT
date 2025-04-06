package org.example.miniprojectspring.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.miniprojectspring.model.dto.response.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitLog {
    private UUID habitLogId;
    private LocalDateTime logDate;
    private Status status;
    private Integer xpEarned;
    private Habit habitId;

}
