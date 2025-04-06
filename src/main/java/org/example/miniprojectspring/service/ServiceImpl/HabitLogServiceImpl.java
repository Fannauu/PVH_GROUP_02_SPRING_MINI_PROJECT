package org.example.miniprojectspring.service.ServiceImpl;

import org.example.miniprojectspring.model.dto.request.HabitLogRequest;
import org.example.miniprojectspring.model.entity.HabitLog;
import org.example.miniprojectspring.repository.HabitLogRepository;
import org.example.miniprojectspring.service.HabitLogService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HabitLogServiceImpl implements HabitLogService {

    private final HabitLogRepository habitlogRepository;
    //inject from Repo
    public HabitLogServiceImpl(HabitLogRepository habitlogRepository) {
        this.habitlogRepository = habitlogRepository;
    }

    // Get By ID Method
    @Override
    public HabitLog getHabitLogByHabitId(UUID habitId) {
        return habitlogRepository.getHabitLogByHabitId(habitId);
    }

    // Post Method
    @Override
    public HabitLog createHabitLog(HabitLogRequest habitLogRequest) {
        return habitlogRepository.createHabitLog(habitLogRequest);
    }

}
