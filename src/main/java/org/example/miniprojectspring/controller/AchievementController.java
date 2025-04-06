package org.example.miniprojectspring.controller;


import io.jsonwebtoken.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.Achievement;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.service.AchievementService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/achievements")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AchievementController {

    private final AchievementService achievementService;

    @Operation(summary = "Get all achievements" )
    @GetMapping
    public ResponseEntity<ApiResponse<List<Achievement>>> getAchievements(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Achievement>>builder()
                        .success(true)
                        .message("Get all achievements successfully")
                        .payload(achievementService.getAchievements(page,size))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Get achievements by App User ID")
    @GetMapping("/app-user")
    public ResponseEntity<ApiResponse<List<Achievement>>> getAchievementByAppUserId(
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(defaultValue = "10") int page,
            @AuthenticationPrincipal AppUser appUser
    ) {
        UUID userId = appUser.getId();
        System.out.println("this is the app user id " + userId);
        return ResponseEntity.ok(
                ApiResponse.<List<Achievement>>builder()
                        .success(true)
                        .message("Get achievements by app user id successfully")
                        .payload(achievementService.getAchievementByAppUserId(userId, size, page))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

}
