package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.dto.request.ProfileRequest;
import org.example.miniprojectspring.model.dto.response.UserDTO;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.model.entity.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
//    UserDTO register(AppUserRequest request);
    UserDTO getAuthenticatedUser();

    UserDTO updateNameAndImgOfUser(ProfileRequest profileRequest);

    UserDTO deleteCurrentUser();
    AppUser register(AppUserRequest request);
    AppUser getUserByEmail(String email);
    void save(AppUser user);
}
