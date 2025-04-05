package org.example.miniprojectspring.service.impl;

import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;
import org.example.miniprojectspring.repository.HabitReposity;
import org.example.miniprojectspring.service.HabitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitServiceImpl implements HabitService {
    private final HabitReposity habitReposity;

    public HabitServiceImpl(HabitReposity habitReposity) {
        this.habitReposity = habitReposity;
    }

    @Override
    public Habit postHabits(HabitRequest habitRequest) {
        return habitReposity.postHabits(habitRequest) ;
    }

    @Override
    public List<Habit> getAllHabits() {
        return habitReposity.getAllHabits();
    }
}
