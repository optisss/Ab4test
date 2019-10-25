package com.ab4system.extremesport.service;

import com.ab4system.extremesport.model.Sport;

import java.util.List;

public interface SportService {

    List<Sport> getSports();

    Sport createSport(Sport sport);
    Sport getSport(Long id);

    Sport updateSport(Long id, Sport sport);
}
