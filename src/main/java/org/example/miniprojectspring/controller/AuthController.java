package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.example.miniprojectspring.exception.NotFoundException;
import org.example.miniprojectspring.jwt.JwtService;
import org.example.miniprojectspring.model.dto.request.AppUserLoginRequest;
import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.dto.response.AuthResponse;
import org.example.miniprojectspring.model.dto.response.UserDTO;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.service.AppUserService;
import org.example.miniprojectspring.service.verfiy.EmailService;
import org.example.miniprojectspring.service.verfiy.OtpEntry;
import org.example.miniprojectspring.service.verfiy.OtpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/auths")

public class AuthController {

    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final OtpService otpService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AppUserService appUserService, AuthenticationManager authenticationManager, JwtService jwtService, OtpService otpService, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.otpService = otpService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
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

    // Verify OTP
    @Operation(summary = "verify-otp Login")
    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<String>> verifyOtp(@RequestParam String email, @RequestParam String otp) throws Exception {
        OtpEntry entry = otpService.getOtpEntry(email);
        if (entry == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.<String>builder()
                            .success(true)
                            .message("opt not found !!")
                            .payload(null)
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
        }

        // Check if OTP expired
        if (LocalDateTime.now().isAfter(entry.getExpiryTime())) {
            otpService.clearOtp(email); // Optional clear expired OTP
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.<String>builder()
                            .success(true)
                            .message("opt are expired!!")
                            .payload(null)
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
        }

        // Validate OTP value
        if (!entry.getOtp().equals(otp)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.<String>builder()
                            .success(true)
                            .message("can't get OTP")
                            .payload(null)
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
        }

        // OTP is valid, proceed with verification
        AppUser user = appUserService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.<String>builder()
                            .success(true)
                            .message("valid opt !!")
                            .payload(null)
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .timestamp(LocalDateTime.now())
                            .build()
            );

        }
        System.out.println(user.toString());

        // Set user as verified
        user.setIsVerified(true);  //
        appUserService.save(user);  //

        // Clear OTP after successful verification
        otpService.clearOtp(email);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<String>builder().build()
        );
    }


    //login
    @Operation(summary = "User Login")
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AppUserLoginRequest appUserLoginRequest) throws Exception {
        // Authenticate passwordlevel
        authenticate(appUserLoginRequest.getIdentifier(), appUserLoginRequest.getPassword());
        // Load user
        final AppUser userDetails = appUserService.getUserByEmail(appUserLoginRequest.getIdentifier());
        AppUser user = appUserService.getUserByEmail(appUserLoginRequest.getIdentifier());
        System.out.println("user2 : " + appUserLoginRequest.getIdentifier());
        if (!user.getIsVerified()) {
            throw new NotFoundException("User " + appUserLoginRequest.getIdentifier() + " are not verified");
        }
        if (!passwordEncoder.matches(appUserLoginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        if (!user.getEmail().equals(appUserLoginRequest.getIdentifier())) {
            throw new BadCredentialsException("Wrong email");
        }

        // Generate token
        final String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(authResponse);
    }


    // Register AppUserRequest
    @Operation(summary = "User Register")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody AppUserRequest appUserRequest) throws Exception {
        UserDTO registeredUser = appUserService.register(appUserRequest);
        if (registeredUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.<UserDTO>builder()
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

        ApiResponse<UserDTO> response = ApiResponse.<UserDTO>builder()
                .success(true)
                .message("User registered successfully! Please verify your email to complete the registration.")
                .httpStatus(HttpStatus.CREATED)
                .payload(registeredUser)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);

    }


    //Resend Email to a new OTP
    @Operation(summary = "Resend otp")
    @PostMapping("/resend")
    public ResponseEntity<ApiResponse> resend(@RequestParam String email) {

        if (appUserService.loadUserByUsername(email).equals(email)) {
            ApiResponse apiResendResponse = ApiResponse.builder()
                    .success(false)
                    .message("Your Email not found!!!!")
                    .httpStatus(HttpStatus.NOT_FOUND)
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
        ApiResponse responseSuccess = ApiResponse.builder()
                .success(true)
                .message("Verification OTP successfully resent to your email.")
                .httpStatus(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(responseSuccess);
    }

}
