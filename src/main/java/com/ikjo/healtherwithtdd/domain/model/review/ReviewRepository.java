package com.ikjo.healtherwithtdd.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikjo.healtherwithtdd.domain.model.Member;
import com.ikjo.healtherwithtdd.domain.model.Review;
import com.ikjo.healtherwithtdd.domain.model.Space;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByMemberAndSpace(Member member, Space space);

	List<Review> findBySpaceOrderByModifiedAtDesc(Space space);
}
