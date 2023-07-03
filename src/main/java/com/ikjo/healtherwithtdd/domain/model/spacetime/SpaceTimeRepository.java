package com.ikjo.healtherwithtdd.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikjo.healtherwithtdd.domain.model.SpaceTime;

@Repository
public interface SpaceTimeRepository extends JpaRepository<SpaceTime, Long> {
	Optional<SpaceTime> findBySpaceId(Long spaceId);
}
