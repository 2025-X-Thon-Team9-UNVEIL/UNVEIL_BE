package com.unveil.domain.user.controller;

import com.unveil.domain.user.dto.UserResponse;
import com.unveil.domain.user.dto.UserSignupRequest;
import com.unveil.domain.user.dto.UserLoginRequest;
import com.unveil.domain.user.service.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "사용자 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;

    @Operation(summary = "일반 회원가입", description = "이메일과 비밀번호로 회원가입을 진행합니다.")
    @PostMapping("/signup")
    public UserResponse signup(@Valid @RequestBody UserSignupRequest request) {
        return userCommandService.signup(request);
    }

    @Operation(summary = "일반 로그인", description = "이메일과 비밀번호로 로그인합니다.")
    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody UserLoginRequest request) {
        return userCommandService.login(request);
    }
}

