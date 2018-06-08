package com.blog.dao;

import java.util.List;

import com.blog.domain.Blog;

public interface BlogDAO {

	public boolean addBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public boolean deleteBlog(int id);
	public Blog getBlog(int id);
	public List<Blog> getBlogList();
}
