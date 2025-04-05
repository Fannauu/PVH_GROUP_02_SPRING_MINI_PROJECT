package org.example.miniprojectspring.service.impl;

import org.example.miniprojectspring.model.entity.Achievement;
import org.example.miniprojectspring.repository.AchievementRepo;
import org.example.miniprojectspring.service.AchievementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepo achievementRepo;

    public AchievementServiceImpl(AchievementRepo achievementRepo) {
        this.achievementRepo = achievementRepo;
    }

    @Override
    public Achievement getAchievementByAppUserId(UUID achievementId) {
        return achievementRepo.getAchievementByAppUserId(achievementId);
    }

    @Override
    public List<Achievement> getAchievements() {
        return achievementRepo.getAchievements();
    }
}
