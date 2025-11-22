package com.unveil.domain.user.service;

import com.unveil.common.TokenDto;
import com.unveil.domain.user.dto.UserResponse;
import com.unveil.domain.user.dto.UserLoginRequest;
import com.unveil.common.exception.BaseException;
import com.unveil.domain.user.exception.UserErrorCode;
import com.unveil.domain.user.repository.UserRepository;
import com.unveil.domain.user.dto.UserSignupRequest;
import com.unveil.domain.user.entity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserValidator userValidator;

    public UserResponse signup(UserSignupRequest userSignupRequest) {
        userValidator.existEmail(userSignupRequest.email());
        String encodedPassword = passwordEncoder.encode(userSignupRequest.password());
        User user = userSignupRequest.toEntity(encodedPassword);

        userRepository.save(user);
        return issueTokenResponse(user);
    }

    public UserResponse login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.email())
                .orElseThrow(() -> BaseException.type(UserErrorCode.USER_NOT_FOUND));

        userValidator.matchPassword(userLoginRequest.password(), user.getPassword());

        return issueTokenResponse(user);
    }

    public UserResponse issueTokenResponse(User user) {
        TokenDto token = tokenService.createAndStoreTokens(user);
        return UserResponse.of(
                token.accessToken(),
                token.refreshToken(),
                token.accessTokenExpiresIn(),
                token.refreshTokenExpiresIn()
        );
    }
}

