package com.ikjo.healtherwithtdd.domain.model.boardlike;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ikjo.healtherwithtdd.domain.model.BaseEntity;
import com.ikjo.healtherwithtdd.domain.model.member.Member;
import com.ikjo.healtherwithtdd.domain.model.board.Board;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at is null")
@SQLDelete(sql = "UPDATE board_like SET board_like.deleted_at = CURRENT_TIMESTAMP WHERE board_like.board_like_id = ?")
@Entity
public class BoardLike extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;

	private boolean isLiked;

	@Builder
	private BoardLike(Member member, Board board, boolean isLiked) {
		this.member = member;
		this.board = board;
		this.isLiked = isLiked;
	}
}
