package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.dto.request.RegisterRequest;
import org.example.miniprojectspring.model.entity.Profile;

import java.util.List;

public interface UserService {

    Profile register(RegisterRequest registerRequest);

    List<Profile> getAllUser();
}
