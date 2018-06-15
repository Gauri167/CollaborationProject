package com.blog.daoImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
        	System.out.println("line 36 of UserIMPL");
			sessionFactory.getCurrentSession().update(user);
			System.out.println("line 38 of UserIMPL");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println("line 42 of UserIMPL");
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

	public User getUser(String email) {
		try {
			System.out.println("line 60 of userIMPL");
			return  (User) sessionFactory.getCurrentSession().createQuery("From User where email like '%"+email+"%'").getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Line 64 of userIMPL");
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

	@SuppressWarnings("deprecation")
	public User validate(String email, String password) {
		try {
			User user=(User) sessionFactory.getCurrentSession().createCriteria(User.class).
					add(Restrictions.eq("email",email)).
					add(Restrictions.eq("password",password)).uniqueResult();
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
