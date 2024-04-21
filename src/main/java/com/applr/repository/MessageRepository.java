package com.applr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.applr.Entity.Message;

public interface MessageRepository extends JpaRepository <Message,Integer> {
		public List<Message> findByChatId(int chatId);
}
