package org.example.miniprojectspring.service.Impl;

import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;
import org.example.miniprojectspring.repository.HabitRepositoty;
import org.example.miniprojectspring.service.HabitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HabitServiceImpl implements HabitService {
    private final HabitRepositoty habitRepositoty;

    public HabitServiceImpl(HabitRepositoty habitRepositoty) {
        this.habitRepositoty = habitRepositoty;
    }

    @Override
    public Habit postHabits(HabitRequest habitRequest) {
        return habitRepositoty.postHabits(habitRequest);
    }

    @Override
    public List<Habit> getAllHabits() {
        return habitRepositoty.getAllHabits();
    }

    @Override
    public Habit deleteHabitById(UUID id) {
        return habitRepositoty.deleteHabitById(id);
    }

    @Override
    public Habit getHabitById(UUID habit_id) {
        return habitRepositoty.getHabitById(habit_id);
    }

    @Override
    public Habit updateHabit(UUID id, HabitRequest habitRequest) {
        return habitRepositoty.updateHabit(id,habitRequest);
    }

}
