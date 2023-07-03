package com.ikjo.healtherwithtdd.api.service.board.response;

import com.ikjo.healtherwithtdd.domain.model.board.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardListResponse {

	private Long id;
	private String name;
	private String title;
	private int commentCount;

	@Builder
	private BoardListResponse(Long id, String nickName, String title, int commentCount) {
		this.id = id;
		this.name = nickName;
		this.title = title;
		this.commentCount = commentCount;
	}

	public static BoardListResponse of(Board board) {
		return BoardListResponse.builder()
			.id(board.getId())
			.nickName(board.getMember().getName())
			.title(board.getTitle())
			.commentCount(board.getComments().size())
			.build();
	}
}
