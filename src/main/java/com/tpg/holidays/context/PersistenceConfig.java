package com.tpg.holidays.context;

import com.tpg.holidays.persistence.entities.ZonedDateTimeConverter;
import com.tpg.holidays.persistence.repositories.HolidaysQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;

@Configuration
@EnableJpaRepositories(basePackages = {"com.tpg.holidays.persistence.repositories"})
@EntityScan(basePackages = {"com.tpg.holidays.persistence.entities"})
public class PersistenceConfig {

    @Autowired
    private EntityManager entityManager;

    @Bean
    public ZonedDateTimeConverter zonedDateTimeConverter() {

        return new ZonedDateTimeConverter();
    }

//    @Bean
//    public HolidaysQueryRepository holidaysQueryRepository() {
//
//        return new HolidaysQueryRepositoryImpl(entityManager);
//    }
}
