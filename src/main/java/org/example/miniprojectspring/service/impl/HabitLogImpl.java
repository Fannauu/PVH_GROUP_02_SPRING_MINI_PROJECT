package org.example.miniprojectspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.request.HabitLogRequest;
import org.example.miniprojectspring.model.dto.response.UserDTO;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.model.entity.HabitLog;
import org.example.miniprojectspring.repository.AppUserRepository;
import org.example.miniprojectspring.repository.HabitLogRepository;
import org.example.miniprojectspring.repository.HabitRepository;
import org.example.miniprojectspring.service.HabitLogService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitLogImpl implements HabitLogService {


    // inject from Repo
    private final HabitLogRepository habitLogRepository;
    private final AppUserRepository appUserRepository;

    // Get By ID Method
    @Override
    public List<HabitLog> getHabitLogByHabitId(UUID id) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Get Current User By Email
        AppUser appUser =  appUserRepository.getUserBYEmail(authentication.getName());
        UserDTO userDTO = appUser.toDto(appUser);
        appUserRepository.getCurrentUserById(userDTO.getId());
        return habitLogRepository.getHabitLogByHabitId(id);
    }

    // Post Method
    @Override
    public HabitLog createHabitLog(HabitLogRequest  habitLogRequest) {

        return habitLogRepository.createHabitLog(habitLogRequest);
    }
}
