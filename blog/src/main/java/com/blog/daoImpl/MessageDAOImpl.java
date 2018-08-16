package com.blog.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.dao.MessageDAO;
import com.blog.domain.Message;

@Repository("messageDAO")
@Transactional
public class MessageDAOImpl implements MessageDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public boolean saveMessage(Message msg) {
		try {
			sessionFactory.getCurrentSession().save(msg);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessage() {
		try {
			return sessionFactory.getCurrentSession().createQuery("from Message").list();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
