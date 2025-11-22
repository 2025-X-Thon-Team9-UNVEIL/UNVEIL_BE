package com.unveil.domain.location.entity;

import com.unveil.domain.user.entity.User;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "locations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 1)
    private String noiseLevel;

    @Column(nullable = false, length = 1)
    private String streetlightLevel;

    @Column(nullable = false, length = 1)
    private String cctvLevel;

    @Column(nullable = false, length = 1)
    private String score;

    @Builder
    public Location(User user, String address, String noiseLevel,
                    String streetlightLevel, String cctvLevel, String score) {
        this.user = user;
        this.address = address;
        this.noiseLevel = noiseLevel;
        this.streetlightLevel = streetlightLevel;
        this.cctvLevel = cctvLevel;
        this.score = score;
    }
}


