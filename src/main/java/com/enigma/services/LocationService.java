package com.enigma.services;

import com.enigma.entity.Location;

import java.util.List;

public interface LocationService {
    Location saveLocation(Location location);

    Location getLocationById(String id);

    List<Location> getAllLocation();

    void deleteLocationById(String id);
}
