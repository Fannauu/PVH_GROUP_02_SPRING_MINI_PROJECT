package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class UserAppController {

//    @Operation(summary = "Get user profile")
//    @GetMapping
//    public ResponseEntity<ApiResponse<AppUser>> getProfile() {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<AppUser>builder()
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
//    public ResponseEntity<ApiResponse<AppUser>> updateProfile() {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<AppUser>builder()
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
//    public ResponseEntity<ApiResponse<AppUser>> deleteProfile() {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<AppUser>builder()
//                        .success(true)
//                        .message("Updated user profile successfully")
//                        .payload(null)
//                        .httpStatus(HttpStatus.OK)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }


}
