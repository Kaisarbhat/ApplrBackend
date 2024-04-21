package com.applr.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applr.Entity.Chat;
import com.applr.Entity.User;
import com.applr.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {
	@Autowired
	private ChatRepository chatRepo;
	@Override
	public Chat createChat(User reqUser, User user2) {
		Chat isExisting = chatRepo.findChatByUsersId(user2, reqUser);
		if(isExisting != null) {
			return isExisting;
		}
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimestamp(LocalDateTime.now());
		return chatRepo.save(chat);
	}

	@Override
	public Chat findChatById(int chatId) throws Exception {
		Optional<Chat> chat = chatRepo.findById(chatId);
		if(chat.isEmpty()) {
			throw new Exception("Chat not found with chatId : "+chatId);
		}
		return chat.get();
	}

	@Override
	public List<Chat> findUsersChat(int userId) {
		return chatRepo.findByUsersId(userId);
	}

}
