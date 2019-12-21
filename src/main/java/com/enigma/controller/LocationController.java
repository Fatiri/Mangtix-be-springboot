package com.enigma.controller;

import com.enigma.entity.Location;
import com.enigma.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @CrossOrigin
    @PostMapping("/location")
    public Location saveLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @CrossOrigin
    @PutMapping("/location")
    public Location updateLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @CrossOrigin
    @GetMapping("/location/{id}")
    public Location getLocationById(@PathVariable String id) {
        return locationService.getLocationById(id);
    }

    @CrossOrigin
    @GetMapping("/locations")
    public List<Location> getAllLocation() {
        return locationService.getAllLocation();
    }

    @CrossOrigin
    @DeleteMapping("/location/{id}")
    public void deleteLocationById(@PathVariable String id) {
        locationService.deleteLocationById(id);
    }

    @CrossOrigin
    @GetMapping("/location-page")
    public Page<Location> getLocationPage(@RequestParam Integer size, @RequestParam Integer page){
        Pageable pageable = PageRequest.of(page, size);
        return locationService.getAllLocationPage(pageable);
    }
}
