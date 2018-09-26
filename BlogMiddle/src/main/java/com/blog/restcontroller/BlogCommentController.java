package com.blog.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dao.BlogCommentDAO;
import com.blog.domain.BlogComment;

@RestController
public class BlogCommentController {

	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@PostMapping(value="/addBlogComment/{blogId}")
	public ResponseEntity<?> addBlogComment(@PathVariable("blogId") int blogId,@RequestBody BlogComment blogComment,HttpSession httpSession)
	{
		String email=(String) httpSession.getAttribute("email");
		blogComment.setBlogId(blogId);
		System.out.println(blogComment.getBlogId());
		blogComment.setUsername(email);
		if(blogCommentDAO.addComment(blogComment))
			return new ResponseEntity<String>("Comment Added Successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Cannot add comment",HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/deleteBlogComment/{commentId}")
	public ResponseEntity<?> deleteBlogComment(@PathVariable("commentId") int commentId)
	{
		if(blogCommentDAO.deleteComment(commentId))
			return new ResponseEntity<String>("COmmenet deleted Successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Cannot delete comment",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/blogCommenetList")
	public ResponseEntity<?> blogCommentList()
	{
		List<BlogComment> list=blogCommentDAO.commentList();
		if(list.size()>0)
			return new ResponseEntity<List<BlogComment>>(list,HttpStatus.OK);
		else
			return new ResponseEntity<String>("No Comment Found",HttpStatus.NOT_FOUND);
	}
}
