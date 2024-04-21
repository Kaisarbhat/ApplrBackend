package com.applr.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applr.Entity.Chat;
import com.applr.Entity.Message;
import com.applr.Entity.User;
import com.applr.repository.ChatRepository;
import com.applr.repository.MessageRepository;
@Service
public class MessageServiceImpl implements MessageService {
	@Autowired 
	private MessageRepository messageRepo;
	@Autowired 
	private ChatService chatService;
	@Autowired 
	private ChatRepository chatRepo;
	
	
	@Override
	public Message createMessage(User user, int chatId, Message req) throws Exception {
		Chat chat =  chatService.findChatById(chatId);
		Message message = new Message();
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		Message savedMessage = messageRepo.save(message);
		chat.getMessages().add(savedMessage);
		chatRepo.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> findChatMessages(int chatId) throws Exception {
		chatService.findChatById(chatId);
		return messageRepo.findByChatId(chatId);
	}

}
