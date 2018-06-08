package com.blog.daoImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.dao.UserDAO;
import com.blog.domain.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public boolean addUser(User user) {
		try {
			user.setRegisterDate(new Date());
			user.setRole('C');
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateUser(User user) {
        try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteUser(String emailId) {
		try {
			User user=getUser(emailId);
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public User getUser(String emailId) {
		try {
			return sessionFactory.getCurrentSession().get(User.class,emailId);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
	try {
		return sessionFactory.getCurrentSession().createQuery("From User").list();
	} catch (HibernateException e) {
		e.printStackTrace();
		return null;
	}
	}

}
