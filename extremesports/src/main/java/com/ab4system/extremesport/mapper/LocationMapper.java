package com.ab4system.extremesport.mapper;

import com.ab4system.extremesport.dto.LocationDTO;
import com.ab4system.extremesport.model.Country;
import com.ab4system.extremesport.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LocationMapper {


    @Mapping(source ="country" ,target = "region.country.name")
    @Mapping(source = "region", target = "region.name")
    @Mapping(source = "locality", target = "name")
    @Mapping(source = "sports", target = "sports")
    Location dtoToEntity(LocationDTO locationDTO);

//    LocationDTO entityToDto(Location location);

    @Mapping(source ="country" ,target = "region.country.name")
    @Mapping(source = "region", target = "region.name")
    @Mapping(source = "locality", target = "name")
    @Mapping(source = "sports", target = "sports")
    void updateLocationFromDto(LocationDTO locationDTO,@MappingTarget Location location);

}
