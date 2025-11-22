package com.unveil.domain.user.service;

import com.unveil.common.exception.BaseException;
import com.unveil.domain.user.exception.UserErrorCode;
import com.unveil.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void existEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw BaseException.type(UserErrorCode.USER_EMAIL_EXIST);
        }
    }

    public void matchPassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw BaseException.type(UserErrorCode.USER_INVALID_PASSWORD);
        }
    }
}

