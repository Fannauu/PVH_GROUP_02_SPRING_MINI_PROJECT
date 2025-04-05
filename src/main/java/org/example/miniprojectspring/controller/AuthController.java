package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.jwt.JwtService;
import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.dto.request.AuthRequest;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.dto.response.AuthResponse;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.service.AppUserSevice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserSevice appUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Operation(summary = "User Register")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AppUser>> register(@RequestBody AppUserRequest appUserRequest){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<AppUser>builder()
                        .success(true)
                        .message("Register user successfully")
                        .payload(null)
                        .payload(appUserService.register(appUserRequest))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    @Operation(summary = "User Login")
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) throws Exception {
        authenticate(request.getEmail(), request.getPassword());
        final UserDetails userDetails = appUserService.loadUserByUsername(request.getEmail());
        final String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(authResponse);
    }

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
