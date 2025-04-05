package org.example.miniprojectspring.controller;


import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class HabitController {
    private final HabitService habitService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabits() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Habit>>builder()
                        .success(true)
                        .message("Get all habits successfully")
                        .payload(habitService.getAllHabits())
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> getHabitById(@PathVariable("habit-id") UUID id) {
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

    @PostMapping
    public ResponseEntity<ApiResponse<Habit>> postHabits(@RequestBody HabitRequest habitRequest) {
        ApiResponse<Habit> response = ApiResponse.<Habit>builder()
                .success(true)
                .message("Post habits successfully")
                .payload(habitService.postHabits(habitRequest))
                .httpStatus(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> updateHabit(@PathVariable("habit-id") UUID id,@RequestBody HabitRequest habitRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Update habits successfully")
                        .payload(habitService.updateHabit(id,habitRequest))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

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
