package com.unveil.domain.location.dto;

import com.unveil.domain.location.entity.Location;
import org.springframework.data.domain.Page;

import java.util.List;

public record LocationListResponse(
        List<LocationResponse> locationList,
        int listSize,
        int totalPage,
        long totalElements,
        boolean isFirst,
        boolean isLast
) {

    public static LocationListResponse from(Page<Location> page) {
        List<LocationResponse> list = page.getContent().stream()
                .map(LocationResponse::from)
                .toList();

        return new LocationListResponse(
                list,
                page.getNumberOfElements(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast()
        );
    }
}


