package com.unveil.domain.location.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unveil.domain.location.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Page<Location> findByUserId(Long userId, Pageable pageable);
}


