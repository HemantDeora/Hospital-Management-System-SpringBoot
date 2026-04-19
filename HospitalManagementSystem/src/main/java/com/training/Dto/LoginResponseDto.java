package com.training.Dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    String jwt;
    Long userId;
}
