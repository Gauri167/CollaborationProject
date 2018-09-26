package com.blog.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dao.BlogLikesDAO;
import com.blog.domain.Blog;
import com.blog.domain.BlogLikes;

@RestController
public class BlogLikesController {
	
	@Autowired
	private BlogLikesDAO blogLikesDAO;

	@GetMapping(value="/hasUserLikedBlog/{blogId}")
	public ResponseEntity<?> hasUserLikedBlog(@PathVariable int blogId,HttpSession httpSession){
		String username=(String) httpSession.getAttribute("username");
		BlogLikes blogLikes=blogLikesDAO.hasUserLikedBlog(blogId, username);
		System.out.println("hasUserLikedBlog controller");
		return new ResponseEntity<BlogLikes>(blogLikes,HttpStatus.OK);
		//if likes is null,response.data=''
		//if likes is 1 object,response.data={bloglikes object}
		
	}
	
	@GetMapping(value="/updateBlogLikes/{blogId}")
	public ResponseEntity<?> updateBlogLikes(@PathVariable int blogId,HttpSession httpSession){
		String email=(String) httpSession.getAttribute("email");
		System.out.println("Update Likes Controller");
		Blog  blog=blogLikesDAO.updateBlogLikes(blogId, email);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
}
