package org.example.miniprojectspring.service;

import org.example.miniprojectspring.model.entity.Achievement;

import java.util.List;
import java.util.UUID;

public interface AchievementService {

    // Fetch achievement by App User ID
    Achievement getAchievementByAppUserId(UUID appUserId,Integer page,Integer size);

    // Fetch all achievements with pagination
    List<Achievement> getAchievements(Integer page, Integer size);
}
