package com.training.Security;

import com.training.Repo.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final AuthUtil authUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        log.info("Incoming request: {}", request.getRequestURI());

        final String authHeader = request.getHeader("Authorization");

        // ✅ FIX 1: correct condition
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ FIX 2: correct token extraction
        String token = authHeader.substring(7);

        String username = authUtil.getUsernameFromToken(token);

        // ✅ FIX 3: check if already authenticated
        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            var user = userRepository.findByUsername(username).orElse(null);

            if (user != null) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                new ArrayList<>() // roles later
                        );

                // 🔥 MOST IMPORTANT
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}

