package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.HabitLogRequest;
import org.example.miniprojectspring.model.entity.HabitLog;

import java.util.UUID;

public interface HabitLogService {
    // Get By ID Method
    HabitLog getHabitLogByHabitId(UUID habitId);
    // Post Method
    HabitLog createHabitLog(HabitLogRequest habitLogRequest);

}
