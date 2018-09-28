package com.blog.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.dao.NotificationDAO;
import com.blog.domain.Notification;

@Repository("notificationDAO")
@Transactional
public class NotificationDAOImpl implements NotificationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getAllNotification(String email) {
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery("from Notification where email:email and viewed=0").setParameter("email",email).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateViewedStatus(int id) {
		Session session=sessionFactory.getCurrentSession();
        Notification notification=session.get(Notification.class,id);
        notification.setViewed(true);
        session.update(notification);
	}

	@Override
	public Notification getNotification(int id) {
		Session session=sessionFactory.getCurrentSession();
        Notification notification=session.get(Notification.class,id);
		return notification;
	}

}
