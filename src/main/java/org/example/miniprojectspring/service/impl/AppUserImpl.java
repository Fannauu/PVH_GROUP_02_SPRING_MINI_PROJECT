package org.example.miniprojectspring.service.impl;

import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.dto.request.ProfileRequest;
import org.example.miniprojectspring.model.dto.response.UserDTO;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.model.entity.Profile;
import org.example.miniprojectspring.repository.AppUserReository;
import org.example.miniprojectspring.service.AppUserSevice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


//    @Override
//    public UserDTO getUserById(Integer id) {
//        AppUser userApp= appUserReository.getUserById(id);
//        UserDTO dTo = userApp.toDto(userApp);
////            UserDTo dTo= new UserDTo(userApp.toDto(userApp));
//        return dTo;
//    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserReository.getUserBYEmail(email);
    }

    public UserDTO getAuthenticatedUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        AppUser appUser = appUserReository.getUserBYEmail(authentication.getName());
        UserDTO userDTO = appUser.toDto(appUser);
        return userDTO;
    }

    @Override
    public UserDTO updateNameAndImgOfUser(ProfileRequest profileRequest) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = appUserReository.updateNameAndImgOfUser(authentication.getName(), profileRequest);
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
        AppUser appUser = appUserReository.deleteCurrentUser(authentication.getName());
//        System.out.println("Name : "+ profileRequest.getName());
        UserDTO userDTO = appUser.toDto(appUser);
        return userDTO;
    }

    @Override
    public UserDTO register(AppUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        AppUser appUser= appUserReository.register(request);
        UserDTO userDTO= appUser.toDto(appUser);
        return userDTO;
    }
}
