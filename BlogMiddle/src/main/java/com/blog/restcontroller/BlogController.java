package com.blog.restcontroller;

import java.util.ArrayList;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BlogController {
	
	@Autowired
	BlogDAO blogDAO;
	
	@PostMapping(value="/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog){
		System.out.println("Adding Blog");
		
		blog.setLikes(0);
		blog.setUsername("Gauri");
		blog.setPostedBy("Gauri Gaur");
		
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
	
	@GetMapping(value="/showBlog/{blogId}")
	public ResponseEntity<?> showBlog(@PathVariable("blogId") int blogId){
		ObjectMapper mapper = new ObjectMapper();
		Blog mblog=blogDAO.getBlog(blogId);
		if(mblog==null)
			return new ResponseEntity<String>("Cannot fetch blog",HttpStatus.NOT_FOUND);
		try {
			mapper.writeValueAsString(mblog);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(blogId);
		System.out.println(mblog.getContent());
			return new ResponseEntity<Blog>(mblog,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/blogList")
	public ResponseEntity<?> getBlogList()
	{
		List<Blog> list=blogDAO.approvedBlogList();
		String jsonInString="";
		List<String> nlist=new ArrayList<String>();
		
		if(list.size()>0) {
			System.out.println(list.size());
			ObjectMapper mapper = new ObjectMapper();
			for(Blog nblog:list)
			{
				try {
					jsonInString = mapper.writeValueAsString(nblog);
					System.out.println(jsonInString);
					nlist.add(jsonInString);
					
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
			return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);
		}
			return new ResponseEntity<String>("unsuccess",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/tobeApprovedBlogs")
	public ResponseEntity<List<Blog>> tobeApprovedBlogList()
	{
		List<Blog> list=blogDAO.notApprovedBlogList();
		System.out.println(list.size());
		if(list.size()>0)
			return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);
		else 
			return new ResponseEntity<List<Blog>>(list,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/acceptBlog/{blogId}")
	public ResponseEntity<?> acceptBlog(@PathVariable("blogId") int blogId){
		blogDAO.acceptBlog(blogId);
		return new ResponseEntity<String>("{\"Blog Accepted\"}",HttpStatus.OK);
	}
}
