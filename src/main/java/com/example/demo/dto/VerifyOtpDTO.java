package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class VerifyOtpDTO {
    @NotBlank(message = "Email không được để trống")
    @Email
    private String email;

    @NotBlank(message = "Mã OTP không được để trống")
    @Size(min = 6, max = 6, message = "OTP gồm đúng 6 chữ số")
    private String otp;
}