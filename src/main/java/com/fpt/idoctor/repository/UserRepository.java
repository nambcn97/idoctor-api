package com.fpt.idoctor.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.idoctor.common.constant.ModelConstants.InitRoleId;
import com.fpt.idoctor.common.util.MapsUtil;
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

	public List<User> getDoctorByStatus(String status) {
		List<User> users = null;
		String hql = "FROM User WHERE status = :status AND role.id = :roleId";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("status", status);
		query.setParameter("roleId", InitRoleId.DOCTOR);
		users = query.list();
		return users;
	}

	public User findByUsername(String username) {
		Session ss = sessionFactory.getCurrentSession();
		Query query = ss.createQuery(
				"from User where username = :username AND role.id != :roleId");
		query.setParameter("username", username.toLowerCase());
		query.setParameter("roleId", InitRoleId.ANONYMOUS);
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
	/**
	 * 
	 * @param lat
	 * @param lng
	 * @param radius
	 *            radius in meters
	 * @return
	 */
	public List<User> findDoctor(Double lat, Double lng, Double radius,
			String[] status) {
		List<User> doctors = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User "
				+ "WHERE role.id = :roleId AND status in (:status)";

		Query query = session.createQuery(hql);
		query.setParameter("roleId", InitRoleId.DOCTOR);
		query.setParameterList("status", status);

		doctors = query.list();
		for (int i = 0; i < doctors.size(); i++) {
			User user = doctors.get(i);
			if (MapsUtil.distFrom(lat, lng, user.getLocation().getLatitude(),
					user.getLocation().getLongitude()) > radius) {
				doctors.remove(user);
				i--;
			}
		}
		return doctors;
	}

	// public void updateDeviceId()

}
