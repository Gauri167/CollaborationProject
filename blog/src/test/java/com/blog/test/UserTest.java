package com.blog.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.blog.dao.UserDAO;
import com.blog.domain.User;

public class UserTest {
	
	@Autowired
	private static UserDAO userDAO;
	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static User user=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.blog");
		context.refresh();
		userDAO=(UserDAO) context.getBean("userDAO");
	}

	@Test
	public void testAddUser() {
		user=new User();
		user.setEmail("gauri1697@yahoo.com");
		user.setPassword("123");
		user.setUsername("Gauri");
		user.setPhone("12345600");
		user.setOnline(false);
		boolean result= userDAO.addUser(user);
		assertTrue("Add User Test Case",result);
	}
    @Ignore
	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}
    @Ignore
	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}
    @Ignore
	@Test
	public void testGetUser() {
		fail("Not yet implemented");
	}
    @Ignore
	@Test
	public void testGetAllUsers() {
		fail("Not yet implemented");
	}

}