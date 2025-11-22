package com.unveil.domain.location.controller;

import com.unveil.domain.location.dto.LocationCreateRequest;
import com.unveil.domain.location.dto.LocationListResponse;
import com.unveil.domain.location.dto.LocationResponse;
import com.unveil.domain.location.service.LocationCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Location", description = "위치 API")
@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationCommandService locationCommandService;

    @Operation(summary = "위치 등록", description = "로그인한 사용자의 위치 정보를 등록합니다.")
    @PostMapping
    public LocationResponse registerLocation(
            @RequestHeader("Authorization") String authorization,
            @Valid @RequestBody LocationCreateRequest request
    ) {
        return locationCommandService.create(authorization, request);
    }

    @Operation(summary = "내 위치 목록 조회", description = "로그인한 사용자가 저장한 모든 위치 정보를 조회합니다.")
    @GetMapping
    public LocationListResponse getMyLocations(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return locationCommandService.getMyLocations(authorization, page, size);
    }
}


