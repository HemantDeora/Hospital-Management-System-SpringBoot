package com.training.Service.Impl;

import com.training.Dto.LoginRequestDto;
import com.training.Dto.LoginResponseDto;
import com.training.Dto.SignResponseDto;
import com.training.Entity.User;
import com.training.Repo.UserRepository;
import com.training.Security.AuthUtil;
import com.training.Service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.builder;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {

   public final AuthUtil authUtil;
    public final AuthenticationManager authenticationManager;
    public final UserRepository userRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId());

    }

    @Override
    public SignResponseDto signup(LoginRequestDto signRequestDto) {

        User user = userRepository.findByUsername(signRequestDto.getUsername()).orElse(null);
        // 1. Check if user already exists
        if (user != null) throw new IllegalArgumentException("User already exists");
        // 2. Create new user
        user = userRepository.save(builder()
                .username(signRequestDto.getUsername())
                .username(signRequestDto.getUsername())
                .password(signRequestDto.getPassword()) // 🔐 important
                .build());

        return new SignResponseDto();
    }
}
