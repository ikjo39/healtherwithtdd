package com.ikjo.healtherwithtdd.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikjo.healtherwithtdd.domain.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	Page<Board> findAll(Pageable pageable);

	Page<Board> findByTitleContaining(String keyword, Pageable pageable);
}
