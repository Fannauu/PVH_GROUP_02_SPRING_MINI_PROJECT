package org.example.miniprojectspring.service.Impl;

import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.repository.AppUerRepository;
import org.example.miniprojectspring.repository.HabitRepositoty;
import org.example.miniprojectspring.service.AppUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUerRepository appUerRepository;

    public AppUserServiceImpl(AppUerRepository appUerRepository) {
        this.appUerRepository = appUerRepository;
    }

    @Override
    public AppUser getUserById(UUID id) {
        return appUerRepository.getUserById(id);
    }
}
