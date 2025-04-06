package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.model.entity.Profile;
import org.example.miniprojectspring.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@SecurityRequirement(name = "bearerAuth")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Operation(summary = "Get user profile")
    @GetMapping
    public ResponseEntity<ApiResponse<AppUser>> getProfile() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<AppUser>builder()
                        .success(true)
                        .message("User profile fetch successfully")
                        .payload(profileService.getAllUsers())
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @Operation(summary = "Update user profile")
    @PutMapping
    public ResponseEntity<ApiResponse<Profile>> updateProfile() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Profile>builder()
                        .success(true)
                        .message("Updated user profile successfully")
                        .payload(null)
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    @Operation(summary = "Delete user profile")
    @DeleteMapping
    public ResponseEntity<ApiResponse<Profile>> deleteProfile() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Profile>builder()
                        .success(true)
                        .message("Updated user profile successfully")
                        .payload(null)
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


}
