package com.ikjo.healtherwithtdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikjo.healtherwithtdd.api.controller.board.BoardController;
import com.ikjo.healtherwithtdd.api.controller.member.MemberController;
import com.ikjo.healtherwithtdd.api.service.board.BoardService;
import com.ikjo.healtherwithtdd.api.service.member.MemberService;
import com.ikjo.healtherwithtdd.api.service.oauth.OAuthService;
import com.ikjo.healtherwithtdd.configuration.SpringSecurityConfig;

@WebMvcTest(
	controllers = {
		MemberController.class,
		BoardController.class
	},
	excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebMvcConfigurer.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SpringSecurityConfig.class)
	}
)
public abstract class ControllerTestSupport {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@MockBean
	protected MemberService memberService;

	@MockBean
	protected OAuthService oAuthService;

	@MockBean
	protected BoardService boardService;
}
