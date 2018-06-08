package com.blog.dao;

import java.util.List;

import com.blog.domain.Forum;

public interface ForumDAO {

	public boolean addForum(Forum forum);
	public boolean updateForum(Forum forum);
	public boolean deleteForum(int id);
	public Forum getForum(int id);
	public List<Forum> getForumList();
}
