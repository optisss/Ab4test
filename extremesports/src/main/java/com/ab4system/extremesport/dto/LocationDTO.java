package com.ab4system.extremesport.dto;

import lombok.Data;

import java.util.List;

@Data
public class LocationDTO {

    private String country;
    private String region;
    private String locality;
    private List<SportDTO> sports;
}
