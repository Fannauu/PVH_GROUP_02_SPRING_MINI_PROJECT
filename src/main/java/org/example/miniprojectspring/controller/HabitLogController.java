package org.example.miniprojectspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.miniprojectspring.model.dto.request.HabitLogRequest;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.HabitLog;
import org.example.miniprojectspring.service.HabitLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habit-logs")
@SecurityRequirement(name = "bearerAuth")
public class HabitLogController {

    private final HabitLogService habitLogService;
    //inject from Service
    public HabitLogController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }

    // Get Method
    @Operation(summary = "Get all habit logs by habit ID ")
    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<List<HabitLog>>> getHabitLogByHabitId(@Valid @PathVariable("habit-id") UUID id , @Positive @RequestParam(defaultValue = "10") Integer size ,@Positive @RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<List<HabitLog>>builder()
                        .success(true)
                        .message("Habit log created successfully")
                        .payload(habitLogService.getHabitLogByHabitId(id,size,page))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Post Method
    @Operation(summary = "Create a new habit log")
    @PostMapping
    public ResponseEntity<ApiResponse<HabitLog>> createHabitLog(@Valid @RequestBody HabitLogRequest habitLogRequest) {
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
