package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.exception.NotFoundException;
import org.example.miniprojectspring.model.dto.request.ProfileRequest;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.dto.response.DeleteApiResponse;
import org.example.miniprojectspring.model.dto.response.UserDTO;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProfileController {
    private final AppUserService appUserService;

    @Operation(summary = "Get user profile")
    @GetMapping
    public ResponseEntity<ApiResponse<UserDTO>> getCurrentUser() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<UserDTO>builder()
                        .success(true)
                        .message("Get user profile successfully")
                        .payload(appUserService.getAuthenticatedUser())
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @Operation(summary = "Update user profile")
    @PutMapping
    public ResponseEntity<ApiResponse<UserDTO>> updateCurrentUser(@RequestBody @Valid ProfileRequest profileRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<UserDTO>builder()
                        .success(true)
                        .message("Updated user profile successfully")
                        .payload(appUserService.updateNameAndImgOfUser(profileRequest))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }


    @Operation(summary = "Delete user profile")
    @DeleteMapping
    public ResponseEntity<DeleteApiResponse<AppUser>> deleteCurrentUser() {
    if (appUserService.deleteCurrentUser() == null){
        throw new NotFoundException("USER NOT FOUND!");
    }
        return ResponseEntity.status(HttpStatus.OK).body(
                DeleteApiResponse.<AppUser>builder()
                        .success(true)
                        .message("Deleted user profile successfully")
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


}
