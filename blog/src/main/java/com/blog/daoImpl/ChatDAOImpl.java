package com.blog.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.dao.ChatDAO;
import com.blog.domain.Chat;

@Repository("chatDAO")
@Transactional
public class ChatDAOImpl implements ChatDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public boolean saveMessage(Chat chat) {
		try {
			sessionFactory.getCurrentSession().save(chat);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Chat> getMessages(String toId,String fromId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Chat> query=session.createQuery("from Chat where toId=:toId and fromId=:fromId")
					.setParameter("toId",toId).setParameter("fromId",fromId);
			List<Chat> list=query.list();
			System.out.println(list.size());
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
