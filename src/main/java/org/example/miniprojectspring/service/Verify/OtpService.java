package org.example.miniprojectspring.service.Verify;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {
    private final Map<String, String> otpCache = new ConcurrentHashMap<>();
    //Save Otp
    public void saveOtp(String email, String otp){
        otpCache.put(email, otp);
    }

    //Verify OTP
    public boolean verifyOtp(String email, String otp){
        return otp.equals(otpCache.get(email));
    }
    // Clear OTP after verification
    public void clearOtp(String email){
        otpCache.remove(email);
    }
}
