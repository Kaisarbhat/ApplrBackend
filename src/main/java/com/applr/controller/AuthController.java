package com.applr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import com.applr.Entity.User;
import com.applr.config.JwtProvider;
import com.applr.repository.UserRepository;
import com.applr.request.LoginRequest;
import com.applr.response.AuthResponse;
import com.applr.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
//	@Autowired
//	private UserServiceImpl userService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
 	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		User isExisting  = userRepo.findByEmail(user.getEmail());
		if(isExisting != null) {
			throw new Exception("Email already Exists");
		}
		User newUser =  new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setGender(user.getGender());
		User savedUser = userRepo.save(newUser);
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword(),null);
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token ,"Registered Successfully");
		
		return res ;
	}
 	
	@PostMapping("/signin")
	public AuthResponse siginin(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		String token = JwtProvider.generateToken(authentication);
		AuthResponse res = new AuthResponse(token,"Logged in Successfully");
		return res;
	}
	private Authentication authenticate (String email,String password) {
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid Email ...");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Credentials");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}
