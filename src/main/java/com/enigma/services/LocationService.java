package com.enigma.services;

import com.enigma.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocationService {
    Location saveLocation(Location location);

    Location getLocationById(String id);

    List<Location> getAllLocation();
    Page<Location> getAllLocationPage(Pageable pageable);
    void deleteLocationById(String id);
}
