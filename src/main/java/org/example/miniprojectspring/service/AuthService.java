package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.entity.AppUser;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    AppUser register(@RequestBody AppUserRequest appUserRequest);
}
