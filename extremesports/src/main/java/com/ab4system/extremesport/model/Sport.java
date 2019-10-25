package com.ab4system.extremesport.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity()
@Table(name = "sport")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;
}
