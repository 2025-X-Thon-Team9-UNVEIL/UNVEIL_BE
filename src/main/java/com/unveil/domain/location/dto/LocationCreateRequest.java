package com.unveil.domain.location.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LocationCreateRequest(

        @NotBlank(message = "주소는 필수입니다.")
        @Schema(example = "서울 중구 필동로1길 30")
        String address,

        @NotBlank(message = "소음 등급은 필수입니다.")
        @Schema(example = "A")
        String noiseLevel,

        @NotBlank(message = "가로등 등급은 필수입니다.")
        @Schema(example = "A")
        String streetlightLevel,

        @NotBlank(message = "CCTV 등급은 필수입니다.")
        @Schema(example = "B")
        String cctvLevel,

        @NotBlank(message = "점수는 필수입니다.")
        @Schema(example = "A")
        String score
) {
}


