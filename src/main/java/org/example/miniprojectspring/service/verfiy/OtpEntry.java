package org.example.miniprojectspring.service.verfiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpEntry {
    private String otp;
    private LocalDateTime expiryTime;

}