package com.ab4system.extremesport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "country")
public class Country extends AbstractBaseEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "country",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Region> regions = new ArrayList<>();

    public void addRegion(Region region) {
        regions.add(region);
        region.setCountry(this);
    }

    public void removeRegion(Region region) {
        regions.remove(region);
        region.setCountry(null);
    }
}
