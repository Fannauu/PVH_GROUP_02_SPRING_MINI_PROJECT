package org.example.miniprojectspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.entity.Achievement;
import org.example.miniprojectspring.repository.AchievementRepository;
import org.example.miniprojectspring.service.AchievementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AchievementImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    @Override
    public List<Achievement> getAchievements() {
        return achievementRepository.getAchievements();
    }

    @Override
    public Achievement getByUserId(UUID userId, int size, int page) {
        return achievementRepository.getByUserId(userId,size,page);
    }
}
