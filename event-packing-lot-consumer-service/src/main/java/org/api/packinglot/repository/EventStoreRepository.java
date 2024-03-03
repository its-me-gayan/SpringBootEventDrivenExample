package org.api.packinglot.repository;

import org.api.packinglot.entity.EventStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStoreRepository extends JpaRepository<EventStore,Integer> {
}
