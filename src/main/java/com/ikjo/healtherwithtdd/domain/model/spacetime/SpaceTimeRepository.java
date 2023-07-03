package com.ikjo.healtherwithtdd.domain.model.spacetime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceTimeRepository extends JpaRepository<SpaceTime, Long> {
}
