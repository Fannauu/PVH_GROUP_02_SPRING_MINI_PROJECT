package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.Achievement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/achievements")
@RequiredArgsConstructor
public class AchievementController {
//
//    @Operation(summary = "Get all achievements" )
//    @GetMapping
//    public ResponseEntity<ApiResponse<List<Achievement>>> getAchievements() {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<List<Achievement>>builder()
//                        .success(true)
//                        .message("Get all achievements successfully")
//                        .payload(null)
//                        .httpStatus(HttpStatus.OK)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//
//    @Operation(summary = "Get achievements by App User ID" )
//    @GetMapping
//    public ResponseEntity<ApiResponse<Achievement>> getAchievementByAppUserId(){
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<Achievement>builder()
//                        .success(true)
//                        .message("Get achievements by app user id successfully")
//                        .payload(null)
//                        .httpStatus(HttpStatus.OK)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }

}
