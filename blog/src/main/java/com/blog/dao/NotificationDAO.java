package com.blog.dao;

import java.util.List;

import com.blog.domain.Notification;

public interface NotificationDAO {

	List<Notification> getAllNotification(String email);
	void updateViewedStatus(int id);
	Notification getNotification(int id);
}
