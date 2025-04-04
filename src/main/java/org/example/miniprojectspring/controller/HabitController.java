package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.Habit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
public class HabitController {

    @Operation(summary = "Get all habits")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabits() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Habit>>builder()
                        .success(true)
                        .message("Get all habits successfully")
                        .payload(null)
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @Operation(summary = "Get habits by ID")
    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> getHabitById(@PathVariable("habit-id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Get habits by ID successfully")
                        .payload(null)
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    @Operation(summary = "Create a new habits")
    @PostMapping
    public ResponseEntity<ApiResponse<Habit>> postHabits() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Post habits successfully")
                        .payload(null)
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @Operation(summary = "Update habits by ID")
    @PutMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> updateHabit(@PathVariable("habit-id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Update habits successfully")
                        .payload(null)
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    @Operation(summary = "Deleted habits by ID")
    @DeleteMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> deleteHabitById(@PathVariable("habit-id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Deleted habits successfully")
                        .payload(null)
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


}
