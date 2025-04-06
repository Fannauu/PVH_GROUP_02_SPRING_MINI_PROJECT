package org.example.miniprojectspring.service.verfiy;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {
    //Map of email -> OTP entry
    private final Map<String, OtpEntry> otpCache = new ConcurrentHashMap<>();
    //Save Otp with 2 minutes expiration
    public void saveOtp(String email, String otp){
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(2);
        otpCache.put(email, new OtpEntry(otp, expiryTime));
    }

    // Retrieve the OTP entry for a given email
    public OtpEntry getOtpEntry(String email){
        return otpCache.get(email);
    }


    // Clear OTP after verification
    public void clearOtp(String email){
        otpCache.remove(email);
    }
}