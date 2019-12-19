package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.entity.Location;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements com.enigma.services.LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public Location saveLocation(Location location){
        return locationRepository.save(location);
    }

    @Override
    public Page<Location> getAllLocationPage(Pageable pageable) {
        return locationRepository.findAll(pageable);
    }

    @Override
    public Location getLocationById(String id){
        if (!locationRepository.findById(id).isPresent()){
            throw new NotFoundException(String.format(MessageConstant.ID_LOCATION_NOT_FOUND, id));
        }
        return locationRepository.findById(id).get();
    }

    @Override
    public List<Location> getAllLocation(){
        return locationRepository.findAll();
    }

    @Override
    public void deleteLocationById(String id){
        locationRepository.deleteById(id);
    }
}
