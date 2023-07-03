package com.ikjo.healtherwithtdd.api.controller.board.request;

import java.util.ArrayList;

import com.ikjo.healtherwithtdd.domain.model.board.Board;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardCreateRequest {

	@NotBlank(message = "게시판 제목은 필수로 기입하여야 합니다.")
	private String title;
	private String content;

	@Builder
	private BoardCreateRequest(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public Board toEntity() {
		return Board.builder()
			.title(title)
			.content(content)
			.comments(new ArrayList<>())
			.likeCount(0)
			.build();
	}
}
