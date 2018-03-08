package com.fpt.idoctor.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.idoctor.model.User;

@Repository
public class UserRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<User> getAllUsers() {
		List<User> users = null;
		Session session = sessionFactory.getCurrentSession();
		users = session.createQuery("FROM User").list();
		return users;
	}

	public User findByUsername(String username) {
		Session ss = sessionFactory.getCurrentSession();
		Query query = ss.createQuery("from User where username = :username");
		query.setParameter("username", username.toLowerCase());
		query.setMaxResults(1);
		List<User> rs = query.list();
		if (rs.isEmpty())
			return null;
		return rs.get(0);
	}

	public User findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (User) session.get(User.class, id);
	}

	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);

	}

	public void updateUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
	}

	public void deleteUser(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, id);
		if (null != user) {
			session.delete(user);
		}
	}

	public User authenticateUser(String username, String encryptedPassword) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"from User where username = :username and password = :password");
		query.setParameter("username", username);
		query.setParameter("password", encryptedPassword);
		query.setMaxResults(1);
		List<User> result = query.list();
		if (result.isEmpty())
			return null;
		else
			return result.get(0);
	}

	public User isExistUser(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from User where username = :username");
		query.setParameter("username", username);
		query.setMaxResults(1);
		List<User> result = query.list();
		if (result.isEmpty())
			return null;
		else
			return result.get(0);
	}

	public boolean existUser(String username) {
		return isExistUser(username) != null;
	}

}
