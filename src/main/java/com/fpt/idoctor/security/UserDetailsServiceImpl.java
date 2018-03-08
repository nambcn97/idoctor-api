package com.fpt.idoctor.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.idoctor.model.Role;
import com.fpt.idoctor.model.User;
import com.fpt.idoctor.repository.RoleRepository;
import com.fpt.idoctor.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	OAuth2ClientContext oauth2Context;
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		oauth2Context.getAccessToken();
		if (username == null || username.isEmpty()) {
			throw new UsernameNotFoundException("User ID is invalid");
		}
		User user = userRepository.findByUsername(username.toLowerCase());

		if (user == null) {
			throw new UsernameNotFoundException("User ID is invalid");
		}

		Role role = user.getRole();

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (role != null) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getCode());
			authorities.add(grantedAuthority);
		}

		return new UserPrincipal(user, authorities);
	}

}
