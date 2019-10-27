package com.ab4system.extremesport.service;

import com.ab4system.extremesport.dto.LocationDTO;
import com.ab4system.extremesport.model.Country;
import com.ab4system.extremesport.model.Location;
import com.ab4system.extremesport.repository.CountryRepository;
import com.ab4system.extremesport.repository.LocationRepository;
import com.ab4system.extremesport.repository.RegionRepository;
import com.ab4system.extremesport.util.AbstractUtilBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class LocationServiceTest extends AbstractUtilBase {

    @Autowired
    private LocationService locationService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private LocationRepository locationRepository;


    public void clearDatabase(){
       countryRepository.deleteAll();
    }

    @Test
    public void testCreateNewHierarchyLocation(){
        clearDatabase();
        LocationDTO locationDTO = createLocationDtoForTest("Romania", "Brasov", "Poiana Brasov","atv");
        Location location = locationService.createLocation(locationDTO);
        assertThat(location.getId(),is(notNullValue()));
        assertThat(location.getRegion().getCountry().getId(),is(notNullValue()));
        assertThat(countryRepository.findAll().size(),is(1));
        assertThat(regionRepository.findAll().size(),is(1));
        assertThat(locationRepository.findAll().size(),is(1));
    }

    @Test
    public void testCreateNewRegionWhenCountryExists(){
        testCreateNewHierarchyLocation();
        LocationDTO newRegionLocationDTO= createLocationDtoForTest("Romania", "Sinaia", "Sinaia","sky");
        Location locationUpdated = locationService.createLocation(newRegionLocationDTO);
        assertThat(locationUpdated.getRegion().getId(),is(notNullValue()));
        assertThat(regionRepository.findAll().size(), is(2));
        assertThat(locationRepository.findAll().size(), is(2));
    }

    @Test
    public void testCreateNewLocationWhenRegionExists(){
        testCreateNewRegionWhenCountryExists();
        LocationDTO newRegionLocationDTO= createLocationDtoForTest("Romania", "Sinaia", "Sinaia sus","snowboard");
        Location locationUpdated = locationService.createLocation(newRegionLocationDTO);
        assertThat(locationRepository.findAll().size(), is(3));
    }
}
