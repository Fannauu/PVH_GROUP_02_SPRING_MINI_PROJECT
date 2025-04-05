package org.example.miniprojectspring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.miniprojectspring.model.dto.request.RegisterRequest;
import org.example.miniprojectspring.model.entity.Profile;
import org.example.miniprojectspring.repository.UserRepository;
import org.example.miniprojectspring.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Profile register(RegisterRequest registerRequest) {
        log.info("request register : {}",registerRequest);
        return userRepository.register(registerRequest);
    }

    @Override
    public List<Profile> getAllUser() {
        return userRepository.getAllUser();
    }
}
