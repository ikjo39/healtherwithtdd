package com.ikjo.healtherwithtdd.api.service.board;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ikjo.healtherwithtdd.IntegrationTestSupport;
import com.ikjo.healtherwithtdd.api.controller.board.request.BoardCreateRequest;
import com.ikjo.healtherwithtdd.api.service.board.response.BoardListResponse;
import com.ikjo.healtherwithtdd.api.service.board.response.BoardResponse;
import com.ikjo.healtherwithtdd.domain.model.board.Board;
import com.ikjo.healtherwithtdd.domain.model.board.BoardRepository;
import com.ikjo.healtherwithtdd.domain.model.member.LoginType;
import com.ikjo.healtherwithtdd.domain.model.member.Member;
import com.ikjo.healtherwithtdd.domain.model.member.MemberRepository;
import com.ikjo.healtherwithtdd.domain.model.member.Role;

class BoardServiceTest extends IntegrationTestSupport {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardService boardService;

	@AfterEach
	void tearDown() {
		boardRepository.deleteAllInBatch();
		memberRepository.deleteAllInBatch();
	}

	@DisplayName("신규 게시판을 등록한다.")
	@Test
	void createBoard() {
		// given
		BoardCreateRequest boardCreateRequest = BoardCreateRequest.builder()
			.title("제목")
			.content("내용")
			.build();

		// when
		BoardResponse boardResponse = boardService.createBoard(boardCreateRequest);

		// then
		assertThat(boardResponse)
			.extracting("id", "member", "title", "content", "likeCount")
			.contains(1L, null, "제목", "내용", 0);
		assertThat(boardResponse.getComments()).hasSize(0);
	}

	@DisplayName("게시판 리스트를 조회한다.")
	@Test
	void getBoardList() {
		// given
		Member member1 = makeMember("홍길동", "000001");
		Member member2 = makeMember("박거세", "000002");
		Board board1 = makeBoard(member1);
		Board board2 = makeBoard(member1);
		Board board3 = makeBoard(member2);

		memberRepository.saveAll(List.of(member1, member2));
		boardRepository.saveAll(List.of(board1, board2, board3));

		// when
		List<BoardListResponse> boardListResponses = boardService.getBoards(0, 2);
		// then
		assertThat(boardListResponses).hasSize(2)
			.extracting("id", "name")
			.containsExactly(
				tuple(3L, "박거세"),
				tuple(2L, "홍길동")
			);
	}

	@DisplayName("제한된 페이지보다 크게 조회하면 빈 리스트를 반환한다.")
	@Test
	void getBoardListWithBigPage() {
		// given
		int targetPagingIndex = 4;
		Member member1 = makeMember("홍길동", "000001");
		Member member2 = makeMember("박거세", "000002");
		Board board1 = makeBoard(member1);
		Board board2 = makeBoard(member1);
		Board board3 = makeBoard(member2);

		memberRepository.saveAll(List.of(member1, member2));
		boardRepository.saveAll(List.of(board1, board2, board3));

		// when
		List<BoardListResponse> boardListResponses = boardService.getBoards(targetPagingIndex, 2);
		// then
		assertThat(boardListResponses).isEqualTo(Collections.emptyList());
	}

	private Member makeMember(String name, String oauthId) {
		return Member.builder()
			.email("test@test.com")
			.name(name)
			.role(Role.USER)
			.oauthId(oauthId)
			.loginType(LoginType.KAKAO)
			.build();
	}

	private Board makeBoard(Member member) {
		return Board.builder()
			.member(member)
			.comments(new ArrayList<>())
			.title("title")
			.content("content")
			.likeCount(0)
			.build();
	}
}
