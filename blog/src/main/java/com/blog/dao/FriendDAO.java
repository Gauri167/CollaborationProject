package com.blog.dao;

import java.util.List;

import com.blog.domain.Friend;
import com.blog.domain.User;

public interface FriendDAO {
	
	List<User> suggestedUserList(String email);
	boolean addFriendRequest(Friend friend);
	List<Friend> getAllPendingRequests(String email);
	void updateFriendRequest(Friend friend);
	void rejectFriendRequest(Friend friend);
	List<User> friendsList(String email);

}
