package com.codenuance.kodeego.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.codenuance.kodeego.model.User;

@Repository
@Qualifier("userRepository")
public class UserRepository extends CrudOperations<User> implements
		UserDetailsService {

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return read(username);
	}
}
