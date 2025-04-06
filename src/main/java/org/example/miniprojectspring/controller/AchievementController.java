package org.example.miniprojectspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.exception.NotFoundException;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.Achievement;
import org.example.miniprojectspring.service.AchievementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/achievements")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementService achievementService;

    // Get all achievements with pagination
    @Operation(summary = "Get all achievements")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Achievement>>> getAchievements(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Achievement> achievements = achievementService.getAchievements(page, size);
        if (achievements.isEmpty()) {
            throw new NotFoundException("No achievements found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Achievement>>builder()
                        .success(true)
                        .message("Get all achievements successfully")
                        .payload(achievements)
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Get achievement by App User ID
    @Operation(summary = "Get achievements by App User ID")
    @GetMapping("/user/{appUserId}")
    public ResponseEntity<ApiResponse<Achievement>> getAchievementByAppUserId(@PathVariable("appUserId") UUID appUserId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Achievement achievement = achievementService.getAchievementByAppUserId(appUserId,page,size);

        // If no achievement is found, throw NotFoundException
        if (achievement == null) {
            throw new NotFoundException("Achievement not found for App User ID: " + appUserId);
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Achievement>builder()
                        .success(true)
                        .message("Get achievement by App User ID successfully")
                        .payload(achievement)
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
