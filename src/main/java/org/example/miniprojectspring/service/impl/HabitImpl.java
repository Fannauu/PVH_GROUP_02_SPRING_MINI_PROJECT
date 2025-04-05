package org.example.miniprojectspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;
import org.example.miniprojectspring.repository.HabitRepository;
import org.example.miniprojectspring.service.HabitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitImpl implements HabitService{

    private final HabitRepository habitRepository;

    @Override
    public List<Habit> getAllHabits() {
        return habitRepository.getAllHabits();
    }

    @Override
    public Habit postHabits(HabitRequest habitRequest) {
        return habitRepository.postHabits(habitRequest);
    }
}
