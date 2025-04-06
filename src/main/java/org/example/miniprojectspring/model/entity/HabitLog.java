package org.example.miniprojectspring.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.miniprojectspring.model.dto.response.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class HabitLog {
    private UUID id;
    private LocalDateTime logDate;
    private Status status;
    private Integer xpEarned;
    private List<HabitLog> habitId;
}
