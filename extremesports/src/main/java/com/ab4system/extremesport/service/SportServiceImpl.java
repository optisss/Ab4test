package com.ab4system.extremesport.service;

import com.ab4system.extremesport.model.Sport;
import com.ab4system.extremesport.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportServiceImpl implements SportService {

    @Autowired
    private SportRepository sportRepository;

    @Override
    public List<Sport> getSports() {
        return sportRepository.findAll();
    }

    @Override
    public Sport createSport(Sport sport) {
        return sportRepository.save(sport);
    }

    @Override
    public Sport getSport(Long id) {
        return sportRepository.findById(id).orElse(null);
    }

    @Override
    public Sport updateSport(Long id, Sport sport) {
        Optional<Sport> sportById = sportRepository.findById(id);
        if (sportById.isPresent()) {
            Sport dbSport = sportById.get();
            dbSport.setEndDate(sport.getEndDate());
            dbSport.setName(sport.getName());
            dbSport.setStartDate(sport.getStartDate());
            dbSport.setPrice(sport.getPrice());
            sportRepository.save(dbSport);
            return dbSport;
        }
        return null;
    }
}
