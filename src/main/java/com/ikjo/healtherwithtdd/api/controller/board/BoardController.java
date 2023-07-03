package com.ikjo.healtherwithtdd.api.controller.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ikjo.healtherwithtdd.api.controller.ApiResponse;
import com.ikjo.healtherwithtdd.api.controller.board.request.BoardCreateRequest;
import com.ikjo.healtherwithtdd.api.service.board.BoardService;
import com.ikjo.healtherwithtdd.api.service.board.response.BoardListResponse;
import com.ikjo.healtherwithtdd.api.service.board.response.BoardResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
@RestController
public class BoardController {

	private final BoardService boardService;

	@PostMapping
	public ApiResponse<BoardResponse> createBoard(@Valid @RequestBody BoardCreateRequest request) {
		return ApiResponse.of(HttpStatus.CREATED, boardService.createBoard(request));
	}

	@GetMapping
	public ApiResponse<List<BoardListResponse>> getBoardList(
		@RequestParam int page,
		@RequestParam @Min(value = 0, message = "사이즈는 0보다 큰값이어야합니다.") int size
	) {
		return ApiResponse.ok(boardService.getBoards(page, size));
	}
}
