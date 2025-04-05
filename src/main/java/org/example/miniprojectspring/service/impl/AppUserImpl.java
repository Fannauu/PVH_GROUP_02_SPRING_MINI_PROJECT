package org.example.miniprojectspring.service.Impl;

import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.repository.AuthRepository;
import org.example.miniprojectspring.service.AppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserImpl implements AppUserService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserImpl(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authRepository.getUserByEmail(email);
    }


    @Override
    public AppUser register(AppUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        AppUser appUser= authRepository.register(request);
        return appUser;
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return authRepository.getUserByEmail(email);
    }

    @Override
    public void save(AppUser user, String email) {
      //  user.setPassword(passwordEncoder.encode(password));
        authRepository.save(user, email);

    }
}
