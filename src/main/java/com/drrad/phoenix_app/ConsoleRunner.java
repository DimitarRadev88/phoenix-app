package com.drrad.phoenix_app;

import com.drrad.phoenix_app.location.dao.LocationRepository;
import com.drrad.phoenix_app.location.dao.MunicipalityRepository;
import com.drrad.phoenix_app.location.dto.MunicipalityDTO;
import com.drrad.phoenix_app.location.enums.LocationType;
import com.drrad.phoenix_app.location.model.Location;
import com.drrad.phoenix_app.location.model.Municipality;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class ConsoleRunner implements CommandLineRunner {

    private final LocationRepository locationRepository;
    private final MunicipalityRepository municipalityRepository;
    private final Gson gson;

    public ConsoleRunner(LocationRepository locationRepository, MunicipalityRepository municipalityRepository, Gson gson) {
        this.locationRepository = locationRepository;
        this.municipalityRepository = municipalityRepository;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        populateLocations();

    }

    private void populateLocations() throws IOException {
        if (locationRepository.count() == 0) {
            MunicipalityDTO[] dto = gson.fromJson(Files.readString(Path.of("src/main/resources/files/locations.json")), MunicipalityDTO[].class);
            System.out.println();

            Arrays.stream(dto).forEach(
                    municipalityDTO -> {
                        Municipality municipality = new Municipality();
                        municipality.setName(municipalityDTO.getName());
                        List<Location> locations = municipalityDTO.getLocations()
                                .stream().map(locationDTO -> {
                                    Location location = new Location();
                                    location.setName(locationDTO.getName());
                                    location.setMunicipality(municipality);
                                    location.setLocationType(locationDTO.getType().equals("с") ? LocationType.VILLAGE : LocationType.TOWN);
                                    return location;
                                }).toList();
                        locationRepository.saveAll(locations);
                    }
            );

        }
    }
}

