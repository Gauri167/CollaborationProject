package com.blog.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.dao.ProfilePicDAO;
import com.blog.domain.ProfilePicture;

@Repository("pictureDAO")
@Transactional
public class ProfilePicDAOImpl implements ProfilePicDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean uploadPic(ProfilePicture picture) {
		try {
			Session session=sessionFactory.getCurrentSession();
			session.saveOrUpdate(picture);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ProfilePicture getPic(String email) {
		Session session=sessionFactory.getCurrentSession();
		ProfilePicture picture=session.get(ProfilePicture.class,email);
		return picture;
	}

}
