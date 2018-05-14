package com.tpg.holidays.persistence.repositories;

import com.tpg.holidays.context.PersistenceConfig;
import com.tpg.holidays.persistence.entities.DestinationEntity;
import com.tpg.holidays.persistence.entities.DestinationEntityFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@Import(value = {PersistenceConfig.class})
@DataJpaTest
@ActiveProfiles({"dev"})
@Profile("dev")
public class DestinationsLifecycleRepositoryTest implements DestinationEntityFixture {

    @Test
    public void save() {

        DestinationEntity newEntity = destinationEntity("SWD", "Swindon", "Swindon");

        DestinationEntity actual = repository.save(newEntity);

        assertEquals((Long) 1000L, actual.getId());
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DestinationsLifecycleRepository repository;
}
