package com.drrad.phoenix_app.location.model;

import com.drrad.phoenix_app.location.enums.LocationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "towns")
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;
    @Column(name = "location_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private LocationType locationType;
    @Column(columnDefinition = "TEXT")
    private String description;

}
