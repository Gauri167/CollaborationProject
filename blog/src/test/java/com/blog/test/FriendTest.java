package com.blog.test;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.blog.dao.FriendDAO;
import com.blog.domain.Friend;

public class FriendTest {
	
	@Autowired
	private static FriendDAO friendDAO;
	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static Friend friend=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.blog");
		context.refresh();
		friendDAO=(FriendDAO) context.getBean("friendDAO");
	}

	@Test
	public void testAddFriend() {
		friend=new Friend();
		friend.setFromId("gauri1697@yahoo.com");
		friend.setToId("167gauri.g@gmail.com");
		friend.setStatus('P');
		boolean status=friendDAO.addFriendRequest(friend);
		assertTrue("add friend testcase",status);
	}

}
