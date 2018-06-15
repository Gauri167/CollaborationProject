package com.blog.daoImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.dao.BlogCommentDAO;
import com.blog.domain.BlogComment;

@Repository("blogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean addComment(BlogComment blogComment) {
		try {
			blogComment.setCommentDate(new Date());
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteComment(int id) {
	   BlogComment blogComment=getComment(id);
	   try {
		sessionFactory.getCurrentSession().delete(blogComment);
		   return true;
	} catch (HibernateException e) {
		e.printStackTrace();
		return false;
	}
	}

	public BlogComment getComment(int id) {
		try {
			return sessionFactory.getCurrentSession().get(BlogComment.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<BlogComment> commentList() {
		try {
			return sessionFactory.getCurrentSession().createQuery("From BlogComment").list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
