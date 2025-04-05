package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserSevice extends UserDetailsService {
    AppUser register(AppUserRequest request);

}
