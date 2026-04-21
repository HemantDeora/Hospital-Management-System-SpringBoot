package com.training.Service;

import com.training.Dto.LoginRequestDto;
import com.training.Dto.LoginResponseDto;
import com.training.Dto.SignResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    SignResponseDto signup(LoginRequestDto signRequestDto);
}
