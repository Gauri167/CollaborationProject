package com.blog.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.blog.dao.ForumDAO;
import com.blog.domain.Forum;

public class ForumTest {

	@Autowired
	private static ForumDAO forumDAO;
	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static Forum forum=null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.blog");
		context.refresh();
		forumDAO=(ForumDAO) context.getBean("forumDAO");
	}

	@Test
	public void testAddForum() 
	{   forum=new Forum();
		forum.setContent("First Forum");
		forum.setUsername("Gauri");
		forum.setTitle("Forum");
		forum.setPostedBy(forum.getUsername());
		boolean result=forumDAO.addForum(forum);
		assertTrue("Forum Add Test Case",result);
	}
    @Ignore
	@Test
	public void testUpdateForum() {
		fail("Not yet implemented");
	}
    @Ignore
	@Test
	public void testDeleteForum() {
		fail("Not yet implemented");
	}
    @Ignore
	@Test
	public void testGetForum() {
		fail("Not yet implemented");
	}
    @Ignore
	@Test
	public void testGetForumList() {
		fail("Not yet implemented");
	}

}
