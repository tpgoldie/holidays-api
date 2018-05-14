package com.tpg.holidays.persistence.repositories;

import com.tpg.holidays.persistence.entities.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface DestinationsLifecycleRepository extends JpaRepository<DestinationEntity, Long> {
}
