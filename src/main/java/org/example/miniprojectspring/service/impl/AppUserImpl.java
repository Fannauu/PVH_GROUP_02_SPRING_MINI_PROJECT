package org.example.miniprojectspring.service.impl;

import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.dto.request.ProfileRequest;
import org.example.miniprojectspring.model.dto.response.UserDTO;
import org.example.miniprojectspring.model.entity.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.example.miniprojectspring.repository.AppUserRepository;
import org.example.miniprojectspring.service.AppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserRepository.getUserBYEmail(email); // Or your method to fetch user by email
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
     // Should print true if user is verified

        return user;
//        return appUserRepository.getUserByEmail(email);
    }

    public UserDTO getAuthenticatedUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        AppUser appUser = appUserRepository.getUserBYEmail(authentication.getName());
        System.out.println("AppUser: " + appUser);
        UserDTO userDTO = appUser.toDto(appUser);
        return userDTO;
    }

    @Override
    public UserDTO updateNameAndImgOfUser(ProfileRequest profileRequest) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = appUserRepository.updateNameAndImgOfUser(authentication.getName(), profileRequest);
        System.out.println("Name : "+ profileRequest.getName());
        UserDTO userDTO = appUser.toDto(appUser);
        System.out.println("Update Profile : " + authentication.getName());
        System.out.println("Update Profile : " + appUser);
        System.out.println("Update Profile : " + userDTO);
        return userDTO;
    }

    @Override
    public UserDTO deleteCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = appUserRepository.deleteCurrentUser(authentication.getName());
        UserDTO userDTO = appUser.toDto(appUser);
        return userDTO;
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return appUserRepository.getUserBYEmail(email);
    }

    @Override
    public void save(AppUser user) {
        appUserRepository.save(user);
    }

    @Override
    public UserDTO register(AppUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        AppUser appUser= appUserRepository.register(request);
        UserDTO userDTO= appUser.toDto(appUser);
        return userDTO;
    }

//    @Override
//    public UserDTO getAuthenticatedUser() {
//        return ;
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}
