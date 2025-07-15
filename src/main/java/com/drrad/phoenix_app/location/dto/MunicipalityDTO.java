package com.drrad.phoenix_app.location.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MunicipalityDTO {

    private String name;
    private List<LocationDTO> locations;

}
