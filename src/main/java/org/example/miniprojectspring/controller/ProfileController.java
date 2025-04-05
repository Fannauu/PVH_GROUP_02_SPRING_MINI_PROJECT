package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/profiles")
//@RequiredArgsConstructor
public class ProfileController {

//    @Operation(summary = "Get user profile")
//    @GetMapping
//    public ResponseEntity<ApiResponse<Profile>> getProfile() {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<Profile>builder()
//                        .success(true)
//                        .message("Get user profile successfully")
//                        .payload(null)
//                        .httpStatus(HttpStatus.OK)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//    @Operation(summary = "Update user profile")
//    @PutMapping
//    public ResponseEntity<ApiResponse<Profile>> updateProfile() {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<Profile>builder()
//                        .success(true)
//                        .message("Updated user profile successfully")
//                        .payload(null)
//                        .httpStatus(HttpStatus.OK)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//
//    @Operation(summary = "Delete user profile")
//    @DeleteMapping
//    public ResponseEntity<ApiResponse<Profile>> deleteProfile() {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<Profile>builder()
//                        .success(true)
//                        .message("Updated user profile successfully")
//                        .payload(null)
//                        .httpStatus(HttpStatus.OK)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }


}
