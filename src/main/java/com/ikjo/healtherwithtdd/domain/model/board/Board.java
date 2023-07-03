package com.ikjo.healtherwithtdd.domain.model.board;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ikjo.healtherwithtdd.domain.model.BaseEntity;
import com.ikjo.healtherwithtdd.domain.model.comment.Comment;
import com.ikjo.healtherwithtdd.domain.model.member.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at is null")
@SQLDelete(sql = "UPDATE board SET board.deleted_at = CURRENT_TIMESTAMP WHERE board.board_id = ?")
@Entity
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Member member;

	@OneToMany(mappedBy = "board")
	private List<Comment> comments;

	private String title;

	private String content;

	private int likeCount;

	@Builder
	private Board(Member member, List<Comment> comments, String title, String content, int likeCount) {
		this.member = member;
		this.comments = comments;
		this.title = title;
		this.content = content;
		this.likeCount = likeCount;
	}
}



