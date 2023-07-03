package com.ikjo.healtherwithtdd.domain.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikjo.healtherwithtdd.domain.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Set<Image>> findAllBySpaceId(Long spaceId);
}
