package com.ikjo.healtherwithtdd.api.controller.board;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import com.ikjo.healtherwithtdd.ControllerTestSupport;
import com.ikjo.healtherwithtdd.api.controller.board.request.BoardCreateRequest;

@WithMockUser
class BoardControllerTest extends ControllerTestSupport {

	@DisplayName("신규 게시판을 등록한다.")
	@Test
	void createBoard() throws Exception {
		// given
		BoardCreateRequest request = BoardCreateRequest.builder()
			.title("title")
			.content("content")
			.build();
		// when	// then
		mockMvc.perform(
				post("/api/v1/boards")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
					.with(csrf())
			).andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.code").value("201"))
			.andExpect(jsonPath("$.status").value("CREATED"))
			.andExpect(jsonPath("$.message").value("CREATED"));
	}

	@DisplayName("신규 게시판을 등록할때 제목은 비워두지 말아야 한다.")
	@Test
	void createBoardWithEmptyTitle() throws Exception {
		// given
		BoardCreateRequest request = BoardCreateRequest.builder()
			.title(null)
			.content("content")
			.build();
		// when	// then
		mockMvc.perform(
				post("/api/v1/boards")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
					.with(csrf())
			).andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.code").value("400"))
			.andExpect(jsonPath("$.status").value("BAD_REQUEST"))
			.andExpect(jsonPath("$.message").value("게시판 제목은 필수로 기입하여야 합니다."))
			.andExpect(jsonPath("$.data").isEmpty());
	}
}
