package com.tpg.holidays.persistence.entities;

public interface DestinationEntityFixture {

    default DestinationEntity destinationEntity(String code, String name, String description) {

        DestinationEntity entity = new DestinationEntity();

        entity.setCode(code);
        entity.setName(name);
        entity.setDescription(description);

        return entity;
    }
}
