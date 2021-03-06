package com.blog.daoImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.dao.BlogDAO;
import com.blog.domain.Blog;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addBlog(Blog blog) {
		try {
			blog.setPostedOn(new Date());
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBlog(Blog blog) {
		try {
		System.out.println(blog.getLikes());
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}	
	}

	public boolean deleteBlog(int id) {
		Blog blog=getBlog(id);
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return true;
		}
	}

	public Blog getBlog(int id) {
		try {
			return sessionFactory.getCurrentSession().get(Blog.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Blog> approvedBlogList() {
		try {
			return sessionFactory.getCurrentSession().createQuery("From Blog where status='A'").list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Blog> notApprovedBlogList() {
		try {
			return sessionFactory.getCurrentSession().createQuery("From Blog where status='NA'").list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Blog> rejectedBlogList() {
		try {
			return sessionFactory.getCurrentSession().createQuery("From Blog where status='R'").list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void acceptBlog(int id) {
		Blog nblog=getBlog(id);
		nblog.setStatus("A");
		updateBlog(nblog);
	}

}
