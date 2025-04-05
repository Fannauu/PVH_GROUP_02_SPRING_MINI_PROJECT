package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.HabitLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/habit-logs")
@RequiredArgsConstructor
public class HabitLogController {

//    @Operation(summary = "Create a new habit log")
//    @PostMapping
//    public ResponseEntity<ApiResponse<HabitLog>> createHabitLog() {
//        return ResponseEntity.status(HttpStatus.CREATED).body(
//                ApiResponse.<HabitLog>builder()
//                        .success(true)
//                        .message("Habit log created successfully")
//                        .payload(null)
//                        .httpStatus(HttpStatus.OK)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//    @Operation(summary = "Get all habit logs by habit ID ")
//    @GetMapping("/{habit-id}")
//    public ResponseEntity<ApiResponse<HabitLog>> getHabitLogByHabitId(@PathVariable("habit-id") Integer id) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(
//                ApiResponse.<HabitLog>builder()
//                        .success(true)
//                        .message("Habit log created successfully")
//                        .payload(null)
//                        .httpStatus(HttpStatus.OK)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }



}
