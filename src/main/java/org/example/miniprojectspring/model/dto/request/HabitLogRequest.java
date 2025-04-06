package org.example.miniprojectspring.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.miniprojectspring.model.dto.response.Status;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitLogRequest {
    private Status status;
    private UUID habitId;
}
