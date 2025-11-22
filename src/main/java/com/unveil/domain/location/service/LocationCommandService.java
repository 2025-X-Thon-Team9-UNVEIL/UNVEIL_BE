package com.unveil.domain.location.service;

import com.unveil.common.exception.BaseException;
import com.unveil.domain.location.dto.LocationCreateRequest;
import com.unveil.domain.location.dto.LocationListResponse;
import com.unveil.domain.location.dto.LocationResponse;
import com.unveil.domain.location.entity.Location;
import com.unveil.domain.location.repository.LocationRepository;
import com.unveil.domain.user.entity.User;
import com.unveil.domain.user.exception.UserErrorCode;
import com.unveil.domain.user.repository.UserRepository;
import com.unveil.domain.user.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationCommandService {

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public LocationResponse create(String authorizationHeader, LocationCreateRequest request) {
        Long userId = tokenService.getUserIdFromToken(authorizationHeader);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> BaseException.type(UserErrorCode.USER_NOT_FOUND));

        Location location = Location.builder()
                .user(user)
                .address(request.address())
                .noiseLevel(request.noiseLevel())
                .safeLevel(request.safeLevel())
                .score(request.score())
                .build();

        locationRepository.save(location);

        return LocationResponse.from(location);
    }

    public LocationListResponse getMyLocations(String authorizationHeader, int page, int size) {
        Long userId = tokenService.getUserIdFromToken(authorizationHeader);

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Location> resultPage = locationRepository.findByUserId(userId, pageRequest);

        return LocationListResponse.from(resultPage);
    }
}


