package com.ab4system.extremesport.controller;

import com.ab4system.extremesport.model.Sport;
import com.ab4system.extremesport.service.SportService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sport")
public class SportRestController {

    @Autowired
    private SportService sportService;

    @GetMapping(path = "/all")
    public List<Sport> getSports() {
        return sportService.getSports();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Sport> getSport(@PathVariable(value = "id") Long id) {
        Sport sport = sportService.getSport(id);
        if (sport == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(sport);
    }

    @PostMapping(path = "/create")
    public Sport createSport(@Valid @RequestBody Sport sport) {
        return sportService.createSport(sport);
    }

    @PutMapping(path = "/update/{id}")
    public Sport updateSport(@PathVariable(value = "id") Long id, @Valid @RequestBody Sport sport) {

        return sportService.updateSport(id, sport);
    }


}
