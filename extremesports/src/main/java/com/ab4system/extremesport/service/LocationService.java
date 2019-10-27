package com.ab4system.extremesport.service;

import com.ab4system.extremesport.dto.LocationDTO;
import com.ab4system.extremesport.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    Location getLocation(Long locationId);

    List<Location> getAllLocation();

    Location createLocation(LocationDTO locationDTO);

    Location deleteLocation(Long locationId);

    Location updateLocation(Long locationId, LocationDTO locationDTO);
}
