package com.blog.daoImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.dao.JobDAO;
import com.blog.domain.Job;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean addJob(Job job) {
		try {
			job.setPostedOn(new Date());
			job.setActive(true);
			System.out.println("line 26 of addJobIMPL");
			sessionFactory.getCurrentSession().save(job);
			//sessionFactory.getCurrentSession().getTransaction().commit();
			System.out.println("line 29 of addJobIMPL");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateJob(Job job) {
	    try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteJob(int id) {
		try {
			Job job=getJob(id);
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Job getJob(int id) {
		try {
			return sessionFactory.getCurrentSession().get(Job.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Job> getActiveJobs() {
		try {
			return sessionFactory.getCurrentSession().createQuery("From Job where active=true").list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Job> getInactiveJobs() {
		try {
			return sessionFactory.getCurrentSession().createQuery("From Job where active=false").list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
