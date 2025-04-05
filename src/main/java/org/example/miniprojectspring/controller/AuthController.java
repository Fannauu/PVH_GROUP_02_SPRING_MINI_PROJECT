package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.example.miniprojectspring.jwt.JwtService;
import org.example.miniprojectspring.model.dto.request.AppUserLoginRequest;
import org.example.miniprojectspring.model.dto.request.AppUserRequest;

import org.example.miniprojectspring.model.dto.response.ApiResendResponse;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.dto.response.AuthResponse;
import org.example.miniprojectspring.model.entity.AppUser;

import org.example.miniprojectspring.service.AppUserService;
import org.example.miniprojectspring.service.Verify.EmailService;
import org.example.miniprojectspring.service.Verify.OtpEntry;
import org.example.miniprojectspring.service.Verify.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
import java.util.Random;

@RestController
@RequestMapping("/api/v1/auths")
public class AuthController {
    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Autowired
    private final OtpService otpService;

    @Autowired
    private final EmailService emailService;
//    @Autowired
//    private UserDetailsPasswordService userDetailsPasswordService;


    public AuthController(AppUserService appUserService, AppUserService appUserService2, AuthenticationManager authenticationManager, JwtService jwtService, OtpService otpService, EmailService emailService) {
        this.appUserService = appUserService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.otpService = otpService;
        this.emailService = emailService;
    }
    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }




    //Verify Controller
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) throws Exception {
        OtpEntry entry = otpService.getOtpEntry(email);
        if (entry == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("OTP NOT FOUND");
        }


        // Check if OTP expired
        if (LocalDateTime.now().isAfter(entry.getExpiryTime())){
            otpService.clearOtp(email); // Optional clear expired OTP
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("OTP EXPIRED, Please resend email to get a new OTP");
        }

        //Validate OTP value
        if (!entry.getOtp().equals(otp)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid OTP, please try again");
        }

        //OTP is valid and not expired, update user verification
        AppUser user = appUserService.getUserByEmail(email);
        if (user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("USER NOT FOUND");

        }
        user.setVerified(true);
        appUserService.save(user,email);
        otpService.clearOtp(email);
        System.out.println();
      return ResponseEntity.ok("OTP verified successfully.");
    }



    @Operation(summary = "User Login")
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AppUserLoginRequest appUserLoginRequest) throws Exception {
        // Load user
        UserDetails userDetails = appUserService.loadUserByUsername(appUserLoginRequest.getIdentifier());
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with email: " + appUserLoginRequest.getIdentifier());
        }
        if (!userDetails.isEnabled()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Account not verified. Please verify your email before logging in.");
        }
        // Authenticate password
        authenticate(appUserLoginRequest.getIdentifier(), appUserLoginRequest.getPassword());

        // Generate token
        final String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(authResponse);
    }


    // Register AppUserRequest
    @Operation(summary = "User Register")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AppUser>> register(@RequestBody AppUserRequest appUserRequest) throws Exception {
        AppUser registeredUser = appUserService.register(appUserRequest);
        if (registeredUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.<AppUser>builder()
                            .success(false)
                            .message("Failed to register the user. Possible duplicate email or validation error.")
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .timestamp(LocalDateTime.now())
                            .build());
        }
        // Send OTP to Email
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

    //Resend Email to a new OTP
    @PostMapping("/resend")
    public ResponseEntity<ApiResendResponse> resend(@RequestParam String email){

        if (appUserService.loadUserByUsername(email).equals(email)) {
            ApiResendResponse apiResendResponse = ApiResendResponse.builder()
                    .success(false)
                    .message("Your Email not found!!!!")
                    .status(HttpStatus.NOT_FOUND)
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.ok(apiResendResponse);
        }
        // Send OTP to Email
        // Generate OTP
        String otp = String.valueOf(new Random().nextInt(900000) + 100000); //  6-digit OTP

        //Store OTP in memory
        otpService.saveOtp(email, otp);

        //Send OTP email
        emailService.sendOtpEmail(email, otp);
        ApiResendResponse responseSuccess = ApiResendResponse.builder()
                .success(true)
                .message("Verification OTP successfully resent to your email.")
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(responseSuccess);
    }


}
