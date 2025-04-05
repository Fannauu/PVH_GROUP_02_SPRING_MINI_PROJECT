package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.miniprojectspring.model.dto.request.RegisterRequest;
import org.example.miniprojectspring.model.dto.response.ApiResponse;

import org.example.miniprojectspring.model.entity.Profile;
import org.example.miniprojectspring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller")
public class AuthController {

    private final UserService userService;

//    @Operation(summary = "Testing")
//    @GetMapping
//    public String test(){
//        return "Test Auth Controller";
//    }

    @GetMapping
    public List<Profile> getAllUser(){
        return userService.getAllUser();
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Profile>> register(@RequestBody RegisterRequest registerRequest){
        log.info("controller request : {}",registerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Profile>builder()
                        .success(true)
                        .message("Register user successfully")
                        .payload(userService.register(registerRequest))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

//    @Operation(summary = "User Login")
//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse<Profile>> login(){
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<Profile>builder()
//                        .success(true)
//                        .message("Post user successfully logged ")
//                        .payload(null)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }


//    @Operation(summary = "User Login")
//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse<Auth>> login(){
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<Auth>builder()
//                        .success(true)
//                        .message("Post user successfully logged ")
//                        .payload(null)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//    @Operation(summary = "Register a new user")
//    @PostMapping("/register")
//    public ResponseEntity<ApiResponse<Auth>> register(){
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<Auth>builder()
//                        .success(true)
//                        .message("Register user successfully")
//                        .payload(null)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//    @Operation(summary = "Verify email with OTP")
//    @PostMapping("/verify")
//    public ResponseEntity<ApiResponse<Auth>> verify(){
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<Auth>builder()
//                        .success(true)
//                        .message("Verify user OTP successfully")
//                        .payload(null)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//    @Operation(summary = "Resend verification OTP")
//    @PostMapping("/verify")
//    public ResponseEntity<ApiResponse<Auth>> resendVerification(){
//        return ResponseEntity.status(HttpStatus.OK).body(
//                ApiResponse.<Auth>builder()
//                        .success(true)
//                        .message("Resend OTP successfully")
//                        .payload(null)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }



}
