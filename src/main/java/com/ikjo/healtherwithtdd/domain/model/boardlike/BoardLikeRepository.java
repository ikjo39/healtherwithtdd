package com.ikjo.healtherwithtdd.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikjo.healtherwithtdd.domain.model.BoardLike;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
}
