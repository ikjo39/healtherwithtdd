package com.ikjo.healtherwithtdd.api.service.board;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikjo.healtherwithtdd.api.controller.board.request.BoardCreateRequest;
import com.ikjo.healtherwithtdd.api.service.board.response.BoardListResponse;
import com.ikjo.healtherwithtdd.api.service.board.response.BoardResponse;
import com.ikjo.healtherwithtdd.domain.model.board.Board;
import com.ikjo.healtherwithtdd.domain.model.board.BoardRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	@Transactional
	public BoardResponse createBoard(BoardCreateRequest boardCreateRequest) {
		Board board = boardCreateRequest.toEntity();
		Board savedBoard = boardRepository.save(board);

		return BoardResponse.of(savedBoard);
	}

	public List<BoardListResponse> getBoards(int pagingIndex, int pagingSize) {
		PageRequest pageRequest
			= PageRequest.of(pagingIndex, pagingSize, Sort.by("modifiedAt").descending());

		return boardRepository.findAll(pageRequest).stream()
			.map(BoardListResponse::of)
			.collect(Collectors.toList());
	}
}
