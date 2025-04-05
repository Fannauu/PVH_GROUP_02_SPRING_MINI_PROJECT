package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.Auth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor

public class AuthController {

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
