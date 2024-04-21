package com.applr.service;

import java.util.List;
import com.applr.Entity.Message;
import com.applr.Entity.User;

public interface MessageService {
      public Message createMessage(User user,int chatId,Message req) throws Exception;
      public List<Message> findChatMessages(int chatId) throws Exception;
}
