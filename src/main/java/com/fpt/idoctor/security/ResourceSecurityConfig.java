package com.fpt.idoctor.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.fpt.idoctor.common.constant.ModelConstants.RoleEnum;

@Configuration
@EnableResourceServer
public class ResourceSecurityConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_id";

	@Autowired
	OAuth2JSONFilter jsonFilter;
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http
				// .csrf().disable()
				.addFilterBefore(jsonFilter, ChannelProcessingFilter.class)
				.authorizeRequests().antMatchers("/oauth/*").permitAll()
				.antMatchers("/auth/*").permitAll().antMatchers("/mobile/*")
				.hasAnyAuthority(RoleEnum.USER.getValue(),
						RoleEnum.DOCTOR.getValue())
				.antMatchers("/get/*").permitAll().and().exceptionHandling()
				.accessDeniedHandler(new AppAccessDeniedHandler());
		// .anyRequest().fullyAuthenticated()

	}

}
