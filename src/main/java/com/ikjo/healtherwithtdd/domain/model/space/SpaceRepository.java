package com.ikjo.healtherwithtdd.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ikjo.healtherwithtdd.domain.model.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
	@Query("select s from Space s"
			+ " join fetch s.spaceTime"
			+ " join fetch s.spaceKinds"
			+ " join fetch s.conveniences"
			+ " join fetch s.images"
			+ " where s.id = :spaceId")
	Optional<Space> findByIdUseFetchJoin(Long spaceId);
}
