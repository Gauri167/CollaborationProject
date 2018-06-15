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

import com.blog.dao.BlogDAO;
import com.blog.domain.Blog;

@RestController
public class BlogController {
	
	@Autowired
	BlogDAO blogDAO;
	
	/*@GetMapping(value="/demo")
	public ResponseEntity<String> demoPurpose(){
		
		return new ResponseEntity<>("Demo Data",HttpStatus.OK);
	}*/
	
	@PostMapping(value="/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog){
		System.out.println("Adding Blog");
		
		blog.setLikes(0);
		blog.setUsername("Gauri");
		blog.setPostedBy("Gauri Gaur");
		blog.setStatus("A");
		
		if(blogDAO.addBlog(blog))
			return new ResponseEntity<String>("{\"message\":\"Success\"}",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);	
	}
	
	@PostMapping(value="/updateBlog/{blogId}")
    public ResponseEntity<String> updateBlog(@PathVariable("blogId") int blogId,@RequestBody Blog blog){
		Blog mblog=blogDAO.getBlog(blogId);
		if(mblog==null)
		{
			System.out.println("Blog with Id"+blogId+"not found");
			return new ResponseEntity<String>("Update Failure",HttpStatus.NOT_FOUND);
		}
		mblog.setLikes(blog.getLikes());
		blogDAO.updateBlog(mblog);
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	
	@GetMapping(value="/blogList")
	public ResponseEntity<List<Blog>> getBlogList()
	{
		List<Blog> list=blogDAO.getBlogList();
		if(list.size()>0)
			return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);
		else
			return new ResponseEntity<List<Blog>>(list,HttpStatus.NOT_FOUND);
	}
}
