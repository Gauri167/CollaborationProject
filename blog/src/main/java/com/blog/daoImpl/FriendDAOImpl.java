package com.blog.daoImpl;



import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.dao.FriendDAO;
import com.blog.domain.Friend;
import com.blog.domain.User;

@SuppressWarnings("deprecation")
@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<User> suggestedUserList(String email) {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings({ "unchecked" })
		SQLQuery<User> query=session.createSQLQuery("select * from User_Table where email in(select email from User_Table where email!=:email minus(select fromId from Friend_Table where toId=:email union select toId from Friend_Table where fromId=:email))")
		                       .setParameter("email",email);
		query.addEntity(User.class);
		List<User> suggestedUsers=query.list();
		System.out.println(suggestedUsers.size());
		return suggestedUsers;
	}

	public boolean addFriendRequest(Friend friend) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(friend);
			return true;
		} catch (Exception e) {
		    e.printStackTrace();
		    return false;
		}

	}

	public List<Friend> getAllPendingRequests(String email) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings({ "unchecked" })
		Query<Friend> query=session.createQuery("from Friend where toId=:toId and status=:status").setParameter("toId",email).setParameter("status",'P');
		List<Friend> list=query.list();
		return list;
	}

	public void updateFriendRequest(Friend friend) {
		try {
			Session session = sessionFactory.getCurrentSession();
			friend.setStatus('F');
			session.update(friend);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}
	
	public void rejectFriendRequest(Friend friend) {
		try {
			Session session = sessionFactory.getCurrentSession();
			friend.setStatus('R');
			session.update(friend);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public List<User> friendsList(String email) {
		Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
		SQLQuery<User> query=session.createSQLQuery("select * from User_Table where email in("+
		                                      "(select fromId from Friend_Table where toId=:email and status='F')"+
        		                              "union"+"(select toId from Friend_Table where fromId=:email and status='F'))").setParameter("email",email);
        query.addEntity(User.class);
        List<User> friends=query.list();
		return friends;
	}

}
