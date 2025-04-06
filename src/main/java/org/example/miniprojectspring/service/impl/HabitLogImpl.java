package org.example.miniprojectspring.service.impl;

import org.example.miniprojectspring.model.dto.request.HabitLogRequest;
import org.example.miniprojectspring.model.entity.HabitLog;
import org.example.miniprojectspring.repository.HabitLogRepository;
import org.example.miniprojectspring.service.HabitLogService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HabitLogImpl implements HabitLogService {

    // inject from Repo
    private final HabitLogRepository habitLogRepository;
    public HabitLogImpl(HabitLogRepository habitLogRepository) {
        this.habitLogRepository = habitLogRepository;
    }

    // Get By ID Method
    @Override
    public HabitLog getHabitLogByHabitId(UUID habitId) {
        return habitLogRepository.getHabitLogByHabitId(habitId);
    }

    // Post Method
    @Override
    public HabitLog createHabitLog(HabitLogRequest  habitLogRequest) {
        return habitLogRepository.createHabitLog(habitLogRequest);
    }
}
