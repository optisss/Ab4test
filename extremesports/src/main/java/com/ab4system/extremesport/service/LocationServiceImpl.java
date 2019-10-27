package com.ab4system.extremesport.service;

import com.ab4system.extremesport.dto.LocationDTO;
import com.ab4system.extremesport.exception.ResourceNotFoundException;
import com.ab4system.extremesport.mapper.LocationMapper;
import com.ab4system.extremesport.model.Country;
import com.ab4system.extremesport.model.Location;
import com.ab4system.extremesport.model.Region;
import com.ab4system.extremesport.model.Sport;
import com.ab4system.extremesport.repository.CountryRepository;
import com.ab4system.extremesport.repository.LocationRepository;
import com.ab4system.extremesport.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegionRepository regionRepository;


    @Override
    public Location getLocation(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    @Override
    public Location createLocation(LocationDTO locationDTO) {

        Location location = extractFromDtoToEntity(locationDTO);
        Region region = location.getRegion();
        Country country = region.getCountry();

        createOrUpdateHierarchicalStructure(location, region, country);
        return location;
    }

    private void createOrUpdateHierarchicalStructure(Location locationFromDTO, Region regionFromDTO, Country countryFromDTO) {
        Country databaseCountry = countryRepository.findByName(countryFromDTO.getName());
        if (databaseCountry == null) {
            persistCountryHierarchy(locationFromDTO, regionFromDTO, countryFromDTO);
        } else {
            Region regionFromDatabase = regionRepository.findByName(regionFromDTO.getName());
            if (regionFromDatabase != null) {
                Location databaseLocation = locationRepository.findByName(locationFromDTO.getName());
                if (databaseLocation != null) {
                    //location exists, update sports
                    updateLocationWithSport(locationFromDTO, databaseLocation);
                } else {
                    //location is new
                    persistLocationHierarchy(locationFromDTO, regionFromDatabase);
                }
            } else {
                //no region in database
                regionFromDTO.addLocationAndSetCountry(locationFromDTO, databaseCountry);
                regionRepository.save(regionFromDTO);
            }

        }
    }


    @Override
    public Location deleteLocation(Long locationId) {
        Optional<Location> locationById = locationRepository.findById(locationId);
        Location location = locationById.orElseThrow(ResourceNotFoundException::new);

        Region region = location.getRegion();
        region.removeLocation(location);
        locationRepository.delete(location);

        return location;
    }

    @Override
    public Location updateLocation(Long locationId, LocationDTO locationDTO) {
        //TODO
        Optional<Location> locationById = locationRepository.findById(locationId);
        Location location = locationById.get();
//        locationMapper.updateLocationFromDto(locationDTO, location);
        locationRepository.save(location);
        return location;
    }

    private void persistCountryHierarchy(Location location, Region region, Country country) {
        country.addRegion(region);
        region.addLocation(location);
        countryRepository.save(country);
    }

    private void persistLocationHierarchy(Location location, Region regionFromDatabase) {
        location.setRegion(regionFromDatabase);
        locationRepository.save(location);
    }

    private void updateLocationWithSport(Location locationDto, Location databaseLocation) {
        for (Sport newDtoSport : locationDto.getSports()) {
            databaseLocation.addSport(newDtoSport);
        }
        locationRepository.save(databaseLocation);
    }

    private Location extractFromDtoToEntity(LocationDTO locationDTO) {
        Location location = locationMapper.dtoToEntity(locationDTO);
        return location;
    }
}
