package org.example.miniprojectspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.model.entity.Habit;
import org.example.miniprojectspring.repository.AppUserRepository;
import org.example.miniprojectspring.repository.HabitRepository;
import org.example.miniprojectspring.service.HabitService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitImpl implements HabitService{

    private final HabitRepository habitRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public List<Habit> getAllHabits() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser =  appUserRepository.getUserBYEmail(authentication.getName());
        List<Habit> habit = habitRepository.getAllHabits(appUser.getEmail());
        return habit;
    }
    @Override
    public Habit postHabits(HabitRequest habitRequest) {
        return habitRepository.postHabits(habitRequest);
    }
}
