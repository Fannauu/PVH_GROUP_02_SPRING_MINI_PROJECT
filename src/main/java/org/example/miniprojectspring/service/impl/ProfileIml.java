package org.example.miniprojectspring.service.Impl;

import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.repository.ProfileRepostitory;
import org.example.miniprojectspring.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileIml implements ProfileService {
    private final ProfileRepostitory profileRepostitory;

    public ProfileIml(ProfileRepostitory profileRepostitory) {
        this.profileRepostitory = profileRepostitory;
    }

    @Override
    public AppUser getAllUsers() {
        return profileRepostitory.getAllUsers();
    }
}
