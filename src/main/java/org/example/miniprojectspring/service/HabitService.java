package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;

import java.util.List;

public interface HabitService {
    List<Habit> getAllHabits();

    Habit postHabits(HabitRequest habitRequest);
}
