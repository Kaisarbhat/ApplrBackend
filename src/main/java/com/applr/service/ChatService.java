package com.applr.service;

import java.util.List;

import com.applr.Entity.Chat;
import com.applr.Entity.User;

public interface ChatService {
		
	public Chat createChat(User reqUser,User user2);
	public Chat findChatById(int chatId) throws Exception;
	public List<Chat>findUsersChat(int userId);
}
