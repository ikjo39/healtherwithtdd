package com.ikjo.healtherwithtdd.domain.model.spacekind;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceKindRepository extends JpaRepository<SpaceKind, Long> {

}
