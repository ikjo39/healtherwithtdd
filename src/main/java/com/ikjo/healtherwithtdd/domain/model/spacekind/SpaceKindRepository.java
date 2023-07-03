package com.ikjo.healtherwithtdd.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikjo.healtherwithtdd.constant.SpaceType;
import com.ikjo.healtherwithtdd.domain.model.SpaceKind;

@Repository
public interface SpaceKindRepository extends JpaRepository<SpaceKind, Long> {
	Optional<Set<SpaceKind>> findAllBySpaceId(Long spaceId);

	@EntityGraph(attributePaths = {"space"})
	Page<SpaceKind> findAllBySpaceTypeIsInAndSpace_TitleContainingIgnoreCase(
		List<SpaceType> spaceType,
		Pageable pageable,
		String title
	);
}
