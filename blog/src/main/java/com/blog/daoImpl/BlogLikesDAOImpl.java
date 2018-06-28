package com.blog.daoImpl;

import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.blog.dao.BlogLikesDAO;
import com.blog.domain.BlogLikes;

@SuppressWarnings("deprecation")
@Repository
@Transactional
public class BlogLikesDAOImpl implements BlogLikesDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public BlogLikes hasUserLikedBlog(int blogId, String username) {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query=session.createQuery("from BlogLikes where blog.id=? and user.username=?");
		query.setInteger(0, blogId);
		query.setString(1,username);
		return (BlogLikes) query.uniqueResult();//it will return either null or 1 object
	}

	public boolean updateBlogLikes(int blogId, String username) {
		BlogLikes blogLikes=hasUserLikedBlog(blogId, username);
		if(blogLikes==null) {
			//glyphicon is in black
			//insert and increment
			// change color to blue
			blogLikes=new BlogLikes();
			
		}
		else {
			//glyphicon is in blue
			//delete and decrement
			// change color to black
		}
		return false;
	}

}
