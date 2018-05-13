package com.tpg.holidays.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "destinations")
@Getter
@Setter
public class DestinationEntity extends BaseEntity {

    private String name;
    private String code;
    private String description;
}
