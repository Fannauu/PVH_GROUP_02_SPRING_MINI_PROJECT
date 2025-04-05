package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.entity.AppUser;

import java.util.List;
import java.util.UUID;

public interface AppUserService {
    AppUser getUserById(UUID id  );
}
