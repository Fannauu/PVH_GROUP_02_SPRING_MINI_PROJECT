package org.example.miniprojectspring.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.miniprojectspring.exception.NotFoundException;
import org.example.miniprojectspring.model.dto.request.ProfileRequest;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.example.miniprojectspring.model.dto.response.DeleteApiResponse;
import org.example.miniprojectspring.model.dto.response.UserDTO;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.model.entity.Profile;
import org.example.miniprojectspring.service.AppUserSevice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProfileController {
    private final AppUserSevice appUserSevice;

//    @GetMapping("/get-current-user")
//    public ResponseEntity<String> getCurrentUser() {
//        AppUser getuser = appUserSevice.getAuthenticatedUser();
//        System.out.println("hdhgh" + getuser);
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//
////        if (!(authentication instanceof AnonymousAuthenticationToken)) {
////            String currentUserName = authentication.getName();
////            return ResponseEntity.ok(currentUserName);
////        } else {
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authenticated user");
////        }
//        return ResponseEntity.ok("Test get current user");
//    }

    @Operation(summary = "Get user profile")
    @GetMapping
    public ResponseEntity<ApiResponse<UserDTO>> getCurrentUser() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<UserDTO>builder()
                        .success(true)
                        .message("Get user profile successfully")
                        .payload(appUserSevice.getAuthenticatedUser())
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @Operation(summary = "Update user profile")
    @PutMapping
    public ResponseEntity<ApiResponse<UserDTO>> updateCurrentUser(@RequestBody ProfileRequest profileRequest) {
//        System.out.println("Update : " + appUserSevice.updateNameAndImgOfUser(profileRequest));
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<UserDTO>builder()
                        .success(true)
                        .message("Updated user profile successfully")
                        .payload(appUserSevice.updateNameAndImgOfUser(profileRequest))
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()

        );

    }


    @Operation(summary = "Delete user profile")
    @DeleteMapping
    public ResponseEntity<DeleteApiResponse<AppUser>> deleteCurrentUser() {
    if (appUserSevice.deleteCurrentUser() == null){
        throw new NotFoundException("USER NOT FOUND!");
    }
//        appUserSevice.deleteCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body(
                DeleteApiResponse.<AppUser>builder()
                        .success(true)
                        .message("Deleted user profile successfully")
                        .httpStatus(HttpStatus.OK)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


}
