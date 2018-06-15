package com.blog.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.blog.dao.BlogCommentDAO;
import com.blog.domain.BlogComment;

public class BlogCommentTest {
	
	@Autowired
	private static BlogCommentDAO blogCommentDAO;
	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static BlogComment blogComment=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.blog");
		context.refresh();
		blogCommentDAO=(BlogCommentDAO) context.getBean("blogCommentDAO");
	}
	@Ignore
	@Test
	public void testAddComment() {
		blogComment=new BlogComment();
		blogComment.setCommentText("Second Comment");
		blogComment.setUsername("Gauri");
		blogComment.setBlogId(41);
		boolean status=blogCommentDAO.addComment(blogComment);
		assertTrue("Add Comment Test",status);
	}
	@Ignore
	@Test
	public void testDeleteComment() {
	boolean status=blogCommentDAO.deleteComment(46);
	assertTrue("Delete Commenet Test",status);
	}
	@Ignore
	@Test
	public void testGetComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testCommentList() {
	List<BlogComment> list=blogCommentDAO.commentList();
	boolean status=true;
	if(list==null)
		status=false;
	assertTrue("Comment List",status);
	}

}
