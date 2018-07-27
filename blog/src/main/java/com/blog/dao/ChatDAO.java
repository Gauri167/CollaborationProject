package com.blog.dao;

import java.util.List;

import com.blog.domain.Chat;

public interface ChatDAO {

	public boolean saveMessage(Chat chat);
	public List<Chat> getMessages(String toId,String fromId);
}
