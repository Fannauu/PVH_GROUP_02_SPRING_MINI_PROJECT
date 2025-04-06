package org.example.miniprojectspring.service.Impl;

import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.repository.AuthRepository;
import org.example.miniprojectspring.service.AppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class AppUserImpl implements AppUserService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserImpl(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        AppUser user =  authRepository.getUserByEmail(email);
//        System.out.println("User verified: " + user.isVerified());  // Debugging line
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                user.isEnabled(),  // This should be based on isVerified, which will be true after email verification
//                true, true, true,   // Always non-expired and non-locked
//                new ArrayList<>()
//        );
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = authRepository.getUserByEmail(email); // Or your method to fetch user by email
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        authRepository.save(user);

     // Should print true if user is verified

        return user;

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
    public void save(AppUser user) {
         authRepository.save(user); // make sure this persists to DB
    }
}
