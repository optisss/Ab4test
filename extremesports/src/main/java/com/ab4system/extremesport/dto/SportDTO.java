package com.ab4system.extremesport.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SportDTO {

    private String name;
    private double pricePerDay;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
