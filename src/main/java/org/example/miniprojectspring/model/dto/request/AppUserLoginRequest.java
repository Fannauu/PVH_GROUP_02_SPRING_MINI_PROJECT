package org.example.miniprojectspring.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserLoginRequest {
// a field that can log in by username or email
    private String identifier;
    private String password;
}
