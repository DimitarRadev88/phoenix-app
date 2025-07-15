package com.drrad.phoenix_app.location;

import com.drrad.phoenix_app.location.enums.LocationType;
import com.drrad.phoenix_app.location.enums.Municipality;
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
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Municipality municipality;
    @Column(name = "location_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private LocationType locationType;

}
