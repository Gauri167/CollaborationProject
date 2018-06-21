package com.blog.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.blog.dao.BlogDAO;
import com.blog.domain.Blog;

public class BlogTest {

	@Autowired
	private static BlogDAO blogDAO;
	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static Blog blog=null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.blog");
		context.refresh();
		blogDAO=(BlogDAO) context.getBean("blogDAO");
	}
    @Ignore
	@Test
	public void testAddBlog() {
		blog=new Blog();
		blog.setLikes(0);
		blog.setContent("This is my blog");
		blog.setShared(0);
		blog.setUsername("Gauri");
		blog.setStatus("My blog");
		blog.setTitle("My First Blog");
		blog.setPostedBy(blog.getUsername());
		boolean result=blogDAO.addBlog(blog);
		assertTrue("Add Blog Test Case",result);
		
	}

	@Ignore
	@Test
	public void testUpdateBlog() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testDeleteBlog() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGetBlog() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetBlogList() {
		List<Blog> list=blogDAO.approvedBlogList();
		boolean status=false;
		if(list.size()>0)
			status=true;
		assertTrue("Approved Blog List Test",status);
	}

}
