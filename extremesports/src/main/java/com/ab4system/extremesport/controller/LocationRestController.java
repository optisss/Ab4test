package com.ab4system.extremesport.controller;

import com.ab4system.extremesport.dto.LocationDTO;
import com.ab4system.extremesport.exception.ResourceNotFoundException;
import com.ab4system.extremesport.model.Location;
import com.ab4system.extremesport.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class LocationRestController {

    @Autowired
    private LocationService locationService;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleExceptions() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/location/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable(value = "id") Long id) {
        Location location = locationService.getLocation(id);
        return ResponseEntity.ok(location);
    }

    @GetMapping(path = "/locations")
    public List<Location> getAllLocation() {
        return locationService.getAllLocation();
    }

    @PostMapping(path = "/location")
    public ResponseEntity<Location> createLocation(@Valid @RequestBody LocationDTO locationDTO) {
        Location location = locationService.createLocation(locationDTO);
        return ResponseEntity.ok(location);
    }

    @PutMapping(path = "/location/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable(value = "id") Long id, @Valid @RequestBody LocationDTO locationDTO) {
        Location location = locationService.updateLocation(id, locationDTO);
        return ResponseEntity.ok(location);
    }

    @DeleteMapping(path = "/location/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable(value = "id") Long id) {
        Location location = locationService.deleteLocation(id);
        return ResponseEntity.ok(location);
    }
}
