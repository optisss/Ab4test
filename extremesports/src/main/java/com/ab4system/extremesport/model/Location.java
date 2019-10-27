package com.ab4system.extremesport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "location")
public class Location extends AbstractBaseEntity {

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "location_sport",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id"))
    private List<Sport> sports = new ArrayList<>();

    public void addSport(Sport sport) {
        sports.add(sport);
        sport.getLocations().add(this);
    }

    public void removeSport(Sport sport) {
        sports.remove(sport);
        sport.getLocations().remove(this);
    }

}
