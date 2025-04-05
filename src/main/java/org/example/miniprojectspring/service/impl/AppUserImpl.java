package org.example.miniprojectspring.service.impl;

import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.repository.AppUserReository;
import org.example.miniprojectspring.service.AppUserSevice;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserImpl implements AppUserSevice {

    private final AppUserReository appUserReository;
    private final PasswordEncoder passwordEncoder;

    public AppUserImpl(AppUserReository appUserReository, PasswordEncoder passwordEncoder) {
        this.appUserReository = appUserReository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserReository.getUserBYEmail(email);
    }


    @Override
    public AppUser register(AppUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        AppUser appUser= appUserReository.register(request);
        return appUser;
    }
}
