package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.Habit;
import org.example.miniprojectspring.service.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habits")
@SecurityRequirement(name = "bearerAuth")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @Operation(summary = "Get all habits")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabits(@Positive @Valid @RequestParam(defaultValue = "10") Integer size ,@Positive @RequestParam(defaultValue = "1") Integer page) {
//        System.out.println("getAllHabits"+ habitService.getAllHabits());
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Habit>>builder()
                        .success(true)
                        .message("Get all habits successfully")
                        .payload(habitService.getAllHabits(size,page))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @Operation(summary = "Get habits by ID")
    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> getHabitById(@Valid @PathVariable("habit-id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Get habits by ID successfully")
                        .payload(habitService.getHabitById(id))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    @Operation(summary = "Create a new habits")
    @PostMapping
    public ResponseEntity<ApiResponse<Habit>> postHabits(@Valid @RequestBody HabitRequest habitRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Post habits successfully")
                        .payload(habitService.postHabits(habitRequest))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @Operation(summary = "Update habits by ID")
    @PutMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> updateHabit(@Valid @PathVariable("habit-id") UUID id, @RequestBody HabitRequest habitRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Update habits successfully")
                        .payload(habitService.updateHabitById(id, habitRequest))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    @Operation(summary = "Deleted habits by ID")
    @DeleteMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> deleteHabitById(@PathVariable("habit-id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Deleted habits successfully")
                        .payload(habitService.deleteHabitById(id))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
