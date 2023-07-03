package com.ikjo.healtherwithtdd.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikjo.healtherwithtdd.domain.model.Board;
import com.ikjo.healtherwithtdd.domain.model.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Page<Comment> findByBoard(Board board, PageRequest pageRequest);
}
