package com.unveil.domain.location.dto;

import com.unveil.domain.location.entity.Location;

import io.swagger.v3.oas.annotations.media.Schema;

public record LocationResponse(

        @Schema(example = "서울 중구 필동로1길 30")
        String address,

        @Schema(example = "A")
        String noiseLevel,

        @Schema(example = "A")
        String streetlightLevel,

        @Schema(example = "B")
        String cctvLevel,

        @Schema(example = "A")
        String score
) {

    public static LocationResponse from(Location location) {
        return new LocationResponse(
                location.getAddress(),
                location.getNoiseLevel(),
                location.getStreetlightLevel(),
                location.getCctvLevel(),
                location.getScore()
        );
    }
}


