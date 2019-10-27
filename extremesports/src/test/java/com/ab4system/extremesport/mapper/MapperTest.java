package com.ab4system.extremesport.mapper;

import com.ab4system.extremesport.dto.LocationDTO;
import com.ab4system.extremesport.dto.SportDTO;
import com.ab4system.extremesport.model.Location;
import com.ab4system.extremesport.util.AbstractUtilBase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;

@SpringBootTest
public class MapperTest extends AbstractUtilBase {

    @Autowired
    private LocationMapper locationMapper;

    @Test
    public void testLocationDtoToEntity(){

        LocationDTO locationDTO = createLocationDtoForTest("Romania", "Brasov", "Poiana Brasov","atv");

        Location location = locationMapper.dtoToEntity(locationDTO);

        assertThat(location.getName(), is("Poiana Brasov"));
        assertThat(location.getRegion().getName(), is("Brasov"));
        assertThat(location.getRegion().getCountry().getName(), is("Romania"));
        assertThat(location.getSports().size(), is(1));


    }

    public void testEntityToDto(){

    }


}
