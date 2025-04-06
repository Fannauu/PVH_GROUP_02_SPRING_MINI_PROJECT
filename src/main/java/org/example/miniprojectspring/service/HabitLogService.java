package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.HabitLogRequest;
import org.example.miniprojectspring.model.entity.HabitLog;

import java.util.List;
import java.util.UUID;

public interface HabitLogService {
    // Get By ID Method
    List<HabitLog> getHabitLogByHabitId(UUID id,Integer size,Integer page);
    // Post Method
    HabitLog createHabitLog(HabitLogRequest habitLogRequest);

}
