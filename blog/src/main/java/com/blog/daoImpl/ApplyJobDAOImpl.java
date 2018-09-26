package com.blog.daoImpl;

import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.dao.ApplyJobDAO;
import com.blog.domain.ApplyJob;

@Repository("ajobDAO")
@Transactional
public class ApplyJobDAOImpl implements ApplyJobDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean apply(ApplyJob ajob) {
		try {
			ajob.setApplyDate(new Date());
			sessionFactory.getCurrentSession().save(ajob);
			return true;
		} catch (HibernateException e) {
		    return false;	
		}
	}

}
