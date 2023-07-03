package com.ikjo.healtherwithtdd;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.ikjo.healtherwithtdd.api.service.oauth.OAuthService;
import com.ikjo.healtherwithtdd.client.oauth.OAuthClient;

@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestSupport {
	@MockBean
	protected OAuthService oAuthService;

	@MockBean
	protected OAuthClient oAuthClient;
}
