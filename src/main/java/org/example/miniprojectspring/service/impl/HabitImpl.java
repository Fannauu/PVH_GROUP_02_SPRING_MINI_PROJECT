package org.example.miniprojectspring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.dto.response.UserDTO;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class HabitImpl implements HabitService{

    private final HabitRepository habitRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public List<Habit> getAllHabits(Integer size, Integer page) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Get Current User By Email
        AppUser appUser =  appUserRepository.getUserBYEmail(authentication.getName());
        UserDTO userDTO = appUser.toDto(appUser);
        // get current user by id
        appUserRepository.getCurrentUserById(userDTO.getId());

        return habitRepository.getAllHabits(size, page);
    }

    @Override
    public Habit postHabits(HabitRequest habitRequest) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Get Current User By Email
        AppUser appUser =  appUserRepository.getUserBYEmail(authentication.getName());
        UserDTO userDTO = appUser.toDto(appUser);
        appUserRepository.getCurrentUserById(userDTO.getId());
        return habitRepository.postHabits(habitRequest, userDTO.getEmail());
    }

    @Override
    public Habit getHabitById(UUID id) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Get Current User By Email
        AppUser appUser =  appUserRepository.getUserBYEmail(authentication.getName());
        UserDTO userDTO = appUser.toDto(appUser);
        // get current user by id
        appUserRepository.getCurrentUserById(userDTO.getId());
        return habitRepository.getHabitById(id);
    }

    @Override
    public Habit updateHabitById(UUID id, HabitRequest habitRequest) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Get Current User By Email
        AppUser appUser =  appUserRepository.getUserBYEmail(authentication.getName());
        UserDTO userDTO = appUser.toDto(appUser);
        // get current user by id
        appUserRepository.getCurrentUserById(userDTO.getId());
        return habitRepository.updateHabitById(id, habitRequest);
    }

    @Override
    public Habit deleteHabitById(UUID id) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Get Current User By Email
        AppUser appUser =  appUserRepository.getUserBYEmail(authentication.getName());
        UserDTO userDTO = appUser.toDto(appUser);
        // get current user by id
        appUserRepository.getCurrentUserById(userDTO.getId());
        return habitRepository.deleteHabitById(id);
    }
}
