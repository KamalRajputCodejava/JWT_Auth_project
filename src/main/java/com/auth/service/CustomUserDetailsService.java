package com.auth.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(username.endsWith("Kamal"))
		{
			return new User("Kamal","kamal@7972", new ArrayList<>());
			
		}
		else {
			throw new UsernameNotFoundException("user not found!");
		  
			
		}
		
	}

}
