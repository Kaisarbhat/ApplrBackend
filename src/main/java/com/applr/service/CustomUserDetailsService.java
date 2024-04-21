package com.applr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.applr.Entity.User;
import com.applr.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =  userRepo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found with the email : "+username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		return  new  org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}

}
