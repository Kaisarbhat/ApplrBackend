package com.applr.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.applr.Entity.User;
import com.applr.exceptions.UserException;
import com.applr.service.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping("/api/users")
	public List<User> getUsersHandler(){
		return userService.findAllUsers();
	}
	
	@GetMapping("/api/users/{userId}")
	public User getUserByIdHandler(@PathVariable int userId) throws UserException {
		return userService.findUserById(userId);
	}
	
	@PutMapping("/api/users")
	public User updateUserHandler(@RequestHeader("Authorization") String jwt,@RequestBody User user) throws UserException {
		User reqUser = userService.findUserByJwt(jwt);
		User updateUser = userService.updateUser(user, reqUser.getId());
		return updateUser;
	}
	
	@DeleteMapping("/api/users/{id}")
	public void  deleteUserHandler(@PathVariable int id) throws UserException {
		 userService.deleteUserById(id);
	}
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization")String jwt,@PathVariable int userId2) throws UserException {
		User reqUser = userService.findUserByJwt(jwt);
		return userService.followUser(reqUser.getId(), userId2);
	}
	@GetMapping("/api/users/search")
	public List<User> searchUserHandler(@RequestParam("query") String query) {
		List<User> users = userService.searchUser(query);
		return users;
	}
	@GetMapping("/api/users/profile")
	public User getUserFromTokenHandler(@RequestHeader("Authorization") String jwt) {
		User user = userService.findUserByJwt(jwt);
//		user.setPassword(null);
		return user;
	}
}
