package com.ab4system.extremesport.util;

import com.ab4system.extremesport.dto.LocationDTO;
import com.ab4system.extremesport.dto.SportDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUtilBase {


    protected LocationDTO createLocationDtoForTest(String country, String region, String locality, String sportname) {
        LocationDTO locationDTO= new LocationDTO();
        locationDTO.setCountry(country);
        locationDTO.setLocality(locality);
        locationDTO.setRegion(region);
        addSports(locationDTO, sportname);
        return locationDTO;
    }



    private void addSports(LocationDTO locationDTO, String sportName) {
        List<SportDTO> sportDTOS= new ArrayList<>();
        SportDTO sportDTO = createSport(sportName);
        sportDTOS.add(sportDTO);
        locationDTO.setSports(sportDTOS);
    }

    private SportDTO createSport(String sportName) {
        SportDTO sportDTO= new SportDTO();
        sportDTO.setEndDate(LocalDateTime.now().plusDays(30));
        sportDTO.setStartDate(LocalDateTime.now());
        sportDTO.setName(sportName);
        sportDTO.setPricePerDay(20.3);
        return sportDTO;
    }

}
