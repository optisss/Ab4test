package com.ab4system.extremesport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "region")
public class Region extends AbstractBaseEntity {

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "region",fetch =FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Location> locations = new ArrayList<>();

    public void addLocation(Location location) {
        locations.add(location);
        location.setRegion(this);
    }

    public void removeLocation(Location location){
        locations.remove(location);
        location.setRegion(null);
    }

}
