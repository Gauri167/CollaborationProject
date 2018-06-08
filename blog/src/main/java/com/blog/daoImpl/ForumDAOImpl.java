package com.blog.daoImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.dao.ForumDAO;
import com.blog.domain.Forum;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean addForum(Forum forum) {
		try {
			forum.setPostedOn(new Date());
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteForum(int id) {
		try {
			Forum forum=getForum(id);
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Forum getForum(int id) {
		try {
			return sessionFactory.getCurrentSession().get(Forum.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Forum> getForumList() {
		try {
			return sessionFactory.getCurrentSession().createQuery("From Forum").list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
