package com.ikjo.healtherwithtdd.domain.model.convenience;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenienceRepository extends JpaRepository<Convenience, Long> {
	Optional<Set<Convenience>> findAllBySpaceId(Long spaceId);
}
