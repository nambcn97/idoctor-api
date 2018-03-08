package com.fpt.idoctor.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.fpt.idoctor.common.constant.AppConstants;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration
		extends
			AuthorizationServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserApprovalHandler userApproverHandler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients)
			throws Exception {

		clients.inMemory().withClient(AppConstants.CLIENT_APP)
				.authorizedGrantTypes("client_credentials", "password",
						"authorization_code", "refresh_token")
				.authorities("ROLE_CLIENT").scopes("read", "write", "trust")
				.secret("secret").accessTokenValiditySeconds(600)
				.refreshTokenValiditySeconds(720);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
		endpoints.tokenStore(tokenStore)
				.userApprovalHandler(userApproverHandler)
				.tokenEnhancer(tokenEnhancerChain())
				.authenticationManager(authenticationManager);

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer)
			throws Exception {
		oauthServer.allowFormAuthenticationForClients();
	}

	public TokenEnhancerChain tokenEnhancerChain() {
		TokenEnhancerChain chain = new TokenEnhancerChain();
		chain.setTokenEnhancers(Arrays.asList(tokenEnhancer()));
		return chain;
	}

	/**
	 * Get the new instance of the token enhancer.
	 * 
	 * @return
	 */
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

}
