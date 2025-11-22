package com.unveil.domain.user.dto;

import lombok.Builder;

@Builder
public record UserResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        Long accessTokenExpiresIn,
        Long refreshTokenExpiresIn
) {
    public static UserResponse of(String accessToken, String refreshToken, long accessTokenExp, long refreshTokenExp) {
        return UserResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .accessTokenExpiresIn(accessTokenExp)
                .refreshTokenExpiresIn(refreshTokenExp)
                .build();
    }
}

