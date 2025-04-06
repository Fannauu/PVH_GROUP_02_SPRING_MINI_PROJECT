package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    AppUser register(AppUserRequest request);
    AppUser getUserByEmail(String email);
    void save(AppUser user);
}
