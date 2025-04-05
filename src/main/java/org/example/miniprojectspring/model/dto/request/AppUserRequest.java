package org.example.miniprojectspring.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppUserRequest {

    private String username;
    private String email;
    private String password;
    private String profileImage;

}