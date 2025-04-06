package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.entity.Achievement;
import org.example.miniprojectspring.model.entity.AppUser;

import java.util.List;
import java.util.UUID;

public interface AchievementService {
    List<Achievement> getAchievements(Integer page, Integer size);

    List<Achievement> getAchievementByAppUserId(UUID userId, Integer page, Integer size);
}
