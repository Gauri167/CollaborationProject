package com.blog.dao;

import com.blog.domain.Blog;
import com.blog.domain.BlogLikes;

public interface BlogLikesDAO {

	BlogLikes hasUserLikedBlog(int blogId,String username);
	//Null will be returned or 1 like object will be returned
	//if null is returned display glyphicon in black
	//if 1 object is returned display glyhpicon in blue
	
	Blog updateBlogLikes(int blogId,String username);
}

