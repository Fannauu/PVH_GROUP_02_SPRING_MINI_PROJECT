package org.example.miniprojectspring.service.impl;

import org.example.miniprojectspring.model.entity.Achievement;
import org.example.miniprojectspring.repository.AchievementRepository;
import org.example.miniprojectspring.service.AchievementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    public AchievementServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public List<Achievement> getAchievements(Integer page, Integer size) {
        return achievementRepository.getAchievements(page, size);
    }

    @Override
    public List<Achievement> getAchievementByAppUserId(UUID userId, Integer page, Integer size) {
        return achievementRepository.getAchievementByAppUserId(userId,page,size);
    }
}
