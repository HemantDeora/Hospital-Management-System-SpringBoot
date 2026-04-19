package com.training.Service;

import com.training.Dto.LoginRequestDto;
import com.training.Dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
