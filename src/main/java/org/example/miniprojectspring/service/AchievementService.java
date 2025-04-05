package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.entity.Achievement;

import java.util.List;
import java.util.UUID;

public interface AchievementService {
    Achievement getAchievementByAppUserId(UUID achievementId);

    List<Achievement> getAchievements();
}
