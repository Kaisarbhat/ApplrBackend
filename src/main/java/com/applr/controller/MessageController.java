package com.applr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.applr.Entity.Message;
import com.applr.Entity.User;
import com.applr.service.MessageService;
import com.applr.service.UserService;

@RestController
public class MessageController {
	    @Autowired
		private MessageService messageService;
	    @Autowired
		private UserService userService;
	    
	    @PostMapping("/api/messages/chat/{chatId}")
	    public Message createMessageHandler(@RequestHeader("Authorization")String jwt,
	    		@RequestBody Message req,@PathVariable int chatId) throws Exception {
	    	User user =  userService.findUserByJwt(jwt);
	    	Message message = messageService.createMessage(user, chatId, req);
	    	return message;
	    }
	    
	    @GetMapping("/api/messages/chat/{chatId}")
	    public List<Message> findChatMessagesHandler(@RequestHeader("Authorization")String jwt,
	    		@PathVariable int chatId) throws Exception {
	    	userService.findUserByJwt(jwt);
	    	List<Message> messages = messageService.findChatMessages(chatId);
	    	return messages;
	    }
}
