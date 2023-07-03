package com.ikjo.healtherwithtdd.api.service.board.response;

import java.util.List;

import com.ikjo.healtherwithtdd.domain.model.board.Board;
import com.ikjo.healtherwithtdd.domain.model.comment.Comment;
import com.ikjo.healtherwithtdd.domain.model.member.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardResponse {

	private Long id;
	private Member member;
	private String title;
	private List<Comment> comments;
	private String content;
	private int likeCount;

	@Builder
	private BoardResponse(Long id, Member member, String title, List<Comment> comments, String content, int likeCount) {
		this.id = id;
		this.member = member;
		this.title = title;
		this.comments = comments;
		this.content = content;
		this.likeCount = likeCount;
	}

	public static BoardResponse of(Board board) {
		return BoardResponse.builder()
			.id(board.getId())
			.member(board.getMember())
			.title(board.getTitle())
			.comments(board.getComments())
			.content(board.getContent())
			.likeCount(board.getLikeCount())
			.build();

	}
}
