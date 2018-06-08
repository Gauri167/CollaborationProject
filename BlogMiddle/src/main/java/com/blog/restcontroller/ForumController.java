package com.blog.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dao.ForumDAO;
import com.blog.domain.Forum;

@RestController
public class ForumController {

	@Autowired
	ForumDAO forumDAO;
	
	@PostMapping(value="/addForum")
	public ResponseEntity<String> addForum(@RequestBody Forum forum)
	{
		if(forumDAO.addForum(forum))
			return new ResponseEntity<String>("Forum added Successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Forum adding failed",HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/updateForum/{forumId}")
	public ResponseEntity<String> updateForum(@PathVariable("forumId") int forumId,@RequestBody Forum forum)
	{
		Forum mforum=forumDAO.getForum(forumId);
		if(mforum==null)
			return new ResponseEntity<String>("Forum update failed ",HttpStatus.NOT_FOUND);
		mforum.setContent(forum.getContent());
		mforum.setPostedBy(forum.getPostedBy());
		mforum.setTitle(forum.getTitle());
		mforum.setUsername(forum.getUsername());
		forumDAO.updateForum(mforum);
		return new ResponseEntity<String>("Forum Update successfully",HttpStatus.OK);
	}
	
	@PostMapping(value="/deleteForum/{forumId}")
	public ResponseEntity<String> deleteForum(@PathVariable("forumId") int forumId)
	{
		if(forumDAO.deleteForum(forumId))
			return new ResponseEntity<String>("Forum deleted Successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Forum delete failed",HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/showForum/{forumId}")
	public ResponseEntity<Forum> showForum(@PathVariable("forumId") int forumId)
	{
		Forum forum=forumDAO.getForum(forumId);
		if(forum==null)
			return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
	
	@GetMapping(value="/showAllForums")
	public ResponseEntity<List<Forum>> forumList()
	{
		List<Forum> list=forumDAO.getForumList();
		if(list.size()>0)
			return new ResponseEntity<List<Forum>>(list,HttpStatus.OK);
		else
			return new ResponseEntity<List<Forum>>(list,HttpStatus.NOT_FOUND);
	}
}
