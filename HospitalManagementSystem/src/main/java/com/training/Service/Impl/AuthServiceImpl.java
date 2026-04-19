package com.training.Service.Impl;

import com.training.Dto.LoginRequestDto;
import com.training.Dto.LoginResponseDto;
import com.training.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }
}
