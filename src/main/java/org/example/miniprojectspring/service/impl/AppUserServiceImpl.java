package org.example.miniprojectspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.repository.AppUserRepository;
import org.example.miniprojectspring.service.AppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username);
    }
}
