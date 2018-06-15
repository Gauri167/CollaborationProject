package com.blog.dao;

import java.util.List;

import com.blog.domain.BlogComment;

public interface BlogCommentDAO {

	public boolean addComment(BlogComment blogComment);
	public boolean deleteComment(int id);
	public BlogComment getComment(int id);
	public List<BlogComment> commentList();
}
