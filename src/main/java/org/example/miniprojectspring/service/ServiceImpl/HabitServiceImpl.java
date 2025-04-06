package org.example.miniprojectspring.service.ServiceImpl;

import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;
import org.example.miniprojectspring.repository.HabitRepository;
import org.example.miniprojectspring.service.HabitService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    //inject from Repo
    public HabitServiceImpl(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    // Post Method
    @Override
    public Habit createHabit(HabitRequest habitRequest) {
        return habitRepository.createHabit(habitRequest);
    }
    // Get Method By ID
    @Override
    public Habit getHabitById(UUID habitId) {
        return habitRepository.getHabitById(habitId);
    }


}
