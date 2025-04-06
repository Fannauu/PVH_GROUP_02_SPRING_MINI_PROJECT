package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<Habit> getAllHabits();
    Habit postHabits(HabitRequest habitRequest);
    Habit getHabitById(UUID id);

    Habit updateHabitById(UUID id, HabitRequest habitRequest);

    Habit deleteHabitById(UUID id);
}
