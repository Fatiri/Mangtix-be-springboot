package com.enigma.controller;

import com.enigma.entity.Location;
import com.enigma.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @PostMapping("/location")
    public Location saveLocation(@RequestBody Location location){
        return locationService.saveLocation(location);
    }

    @PutMapping("/location")
    public Location updateLocation(@RequestBody Location location){
        return locationService.saveLocation(location);
    }

    @GetMapping("/location/{id}")
    public Location getLocationById(@PathVariable String id){
        return locationService.getLocationById(id);
    }

    @GetMapping("/locations")
    public List<Location> getAllLocation(){
        return locationService.getAllLocation();
    }

    @DeleteMapping("/location/{id}")
    public void deleteLocationById(@PathVariable String id){
        locationService.deleteLocationById(id);
    }
}
