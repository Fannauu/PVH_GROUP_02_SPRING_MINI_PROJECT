package org.example.miniprojectspring.service.Impl;

import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.repository.AuthRepository;
import org.example.miniprojectspring.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class ImplAuth implements AuthService {
    private final AuthRepository authRepository;

    public ImplAuth(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public AppUser register(AppUserRequest appUserRequest) {
        AppUser appUser = authRepository.register(appUserRequest);
        System.out.println(appUser.toString());
        return authRepository.register(appUserRequest);
    }
}
