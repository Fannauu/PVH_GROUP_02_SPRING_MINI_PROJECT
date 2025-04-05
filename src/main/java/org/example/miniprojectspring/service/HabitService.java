package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;
import org.springframework.stereotype.Service;

@Service
public interface HabitService {
    //Post Method
    Habit createHabit(HabitRequest habitRequest);

}
