package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;


import java.util.UUID;


public interface HabitService {
    //Post Method
    Habit createHabit(HabitRequest habitRequest);
    //Get Method By ID
    Habit getHabitById(UUID habitId);
}
