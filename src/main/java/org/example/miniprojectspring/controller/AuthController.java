package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.dto.request.AuthenticationRequest;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.model.entity.Auth;
import org.example.miniprojectspring.service.AuthService;
import org.example.miniprojectspring.service.Impl.ImplAuth;
import org.example.miniprojectspring.service.Verify.EmailService;
import org.example.miniprojectspring.service.Verify.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/auths")
public class AuthController {
    // Send OTP to Email
    @Autowired
    private final OtpService otpService;

    @Autowired
    private final EmailService emailService;

    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("email", "");
        return "otp_form";
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email, Model model) {
        model.addAttribute("email", email);
        return "verify_otp";
    }


    //Verify Controller
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp, Model model){
        boolean isValid = otpService.verifyOtp(email, otp);
        model.addAttribute("result", isValid ? "Valid OTP" : "Invalid OTP");
        return "result";
    }


    private final AuthService authService;
    public AuthController(OtpService otpService, EmailService emailService, AuthService authService) {
        this.otpService = otpService;
        this.emailService = emailService;
        this.authService = authService;
    }
    // Register AppUserRequest
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AppUser>> register(@RequestBody AppUserRequest appUserRequest) {
            AppUser registeredUser = authService.register(appUserRequest);
            if (registeredUser == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ApiResponse.<AppUser>builder()
                                .success(false)
                                .message("Failed to register the user. Possible duplicate email or validation error.")
                                .httpStatus(HttpStatus.BAD_REQUEST)
                                .timestamp(LocalDateTime.now())
                                .build());
            }


        // Generate OTP
        String otp = String.valueOf(new Random().nextInt(900000) + 100000); //  6-digit OTP

        //Store OTP in memory
        otpService.saveOtp(registeredUser.getEmail(), otp);

        //Send OTP email
        emailService.sendOtpEmail(registeredUser.getEmail(), otp);




            ApiResponse<AppUser> response = ApiResponse.<AppUser>builder()
                    .success(true)
                    .message("User registered successfully! Please verify your email to complete the registration.")
                    .httpStatus(HttpStatus.CREATED)
                    .payload(registeredUser)
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.ok(response);
        }






}
