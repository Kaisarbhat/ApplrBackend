package com.applr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.applr.Entity.Chat;
import com.applr.Entity.User;
import com.applr.request.ChatRequest;
import com.applr.service.ChatService;
import com.applr.service.UserService;

@RestController
public class ChatController {
	@Autowired
	private ChatService chatService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/chat")
	public Chat createChat(
	@RequestHeader("Authorization") String jwt,
	@RequestBody ChatRequest req) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		User user2 = userService.findUserById(req.getUserId());
		Chat chat = chatService.createChat(reqUser, user2);
		return chat;
	}
	@GetMapping("/api/chat")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
		User user = userService.findUserByJwt(jwt);
		List<Chat> chats = chatService.findUsersChat(user.getId());
		return chats;
	}
	
}
