package com.blog.dao;

import java.util.List;

import com.blog.domain.Message;

public interface MessageDAO {

	public boolean saveMessage(Message msg);
	public List<Message> getMessage();
}
