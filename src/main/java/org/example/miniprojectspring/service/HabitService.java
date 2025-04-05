package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    Habit postHabits(HabitRequest habitRequest);
    List<Habit>  getAllHabits();
    Habit deleteHabitById(UUID id);
    Habit getHabitById(UUID id);
    Habit updateHabit(UUID id, HabitRequest habitRequest);
}
