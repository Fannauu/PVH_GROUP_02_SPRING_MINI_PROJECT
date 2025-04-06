package org.example.miniprojectspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.configuration.SecurityUtil;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitImpl implements HabitService{

    private final HabitRepository habitRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public List<Habit> getAllHabits(String email) {
        // Optional: if you want to return all habits regardless of user
        return habitRepository.getAllHabits(email); // Only if your query supports null
    }

//    @Override
//    public List<Habit> getAllHabits(String email) {
//        return habitRepository.getAllHabits(email);
//    }

    @Override
    public List<Habit> getCurrentUserHabits() {
        String email = SecurityUtil.getCurrentUserEmail();
        System.out.println("Current authenticated user email: " + email); // ✅ Print it

        if (email == null) {
            throw new RuntimeException("Unauthorized access - no logged-in user");
        }
        return getAllHabits(email);
    }



    //    @Override
//    public List<Habit> getAllHabits() {
//        String email = SecurityUtil.getCurrentUserEmail();
//        return habitRepository.getAllHabits(email);
//    //        List<Habit> habit = habitRepository.getAllHabits(appUser.getEmail());
//    //        return habit;
//    }
    @Override
    public Habit postHabits(HabitRequest habitRequest) {
        System.out.println("Habit post"+habitRequest.getTitle());
        return habitRepository.postHabits(habitRequest);
    }

    @Override
    public Habit getHabitById(UUID id) {
        return habitRepository.getHabitById(id);
    }

    @Override
    public Habit updateHabitById(UUID id, HabitRequest habitRequest) {
        System.out.println("updateHabitById"+ habitRepository.updateHabitById(id,habitRequest));
        return habitRepository.updateHabitById(id,habitRequest);
    }

    @Override
    public Habit deleteHabitById(UUID id) {
        return habitRepository.deleteHabitById(id);
    }




}
