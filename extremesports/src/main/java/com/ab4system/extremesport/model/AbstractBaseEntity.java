package com.ab4system.extremesport.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue
    private Long id;

}
