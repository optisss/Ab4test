package com.ab4system.extremesport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity()
@Table(name = "sport")
public class Sport extends AbstractBaseEntity{

    @Column
    private String name;

    @Column
    private double pricePerDay;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @ManyToMany(mappedBy = "sports", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Location> locations =  new ArrayList<>();

    /**
     *
     * This has to be decided, when a sport is the same, same price, name and start/end date?
     * <br/>
     * Or same name and price.
     * Or just name.
     * First decision is to have the same name, price, and start/end date.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sport sport = (Sport) o;
        return Double.compare(sport.pricePerDay, pricePerDay) == 0 &&
                name.equals(sport.name) &&
                startDate.equals(sport.startDate) &&
                endDate.equals(sport.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pricePerDay, startDate, endDate);
    }
}
