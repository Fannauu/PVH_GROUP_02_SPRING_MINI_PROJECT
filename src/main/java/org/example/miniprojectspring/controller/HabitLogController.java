package org.example.miniprojectspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.miniprojectspring.model.dto.request.HabitLogRequest;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.HabitLog;
import org.example.miniprojectspring.service.HabitLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habit-logs")
public class HabitLogController {

    private final HabitLogService habitLogService;
    //inject from Service
    public HabitLogController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }

    // Get Method
    @Operation(summary = "Get all habit logs by habit ID ")
    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<HabitLog>> getHabitLogByHabitId(@PathVariable("habit-id") UUID habitId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<HabitLog>builder()
                        .success(true)
                        .message("Habit log created successfully")
                        .payload(habitLogService.getHabitLogByHabitId(habitId))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Post Method
    @Operation(summary = "Create a new habit log")
    @PostMapping
    public ResponseEntity<ApiResponse<HabitLog>> createHabitLog(@RequestBody HabitLogRequest habitLogRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<HabitLog>builder()
                        .success(true)
                        .message("Habit log created successfully")
                        .payload(habitLogService.createHabitLog(habitLogRequest))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

}
