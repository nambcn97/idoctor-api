package com.fpt.idoctor.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.idoctor.common.constant.ModelConstants.InitRoleId;
import com.fpt.idoctor.model.Role;

@Repository
public class RoleRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Role> getAllRoles() {
		Session session = sessionFactory.getCurrentSession();
		List<Role> listRole = session.createQuery("FROM Role").list();
		return listRole;
	}

	public void addRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.save(role);
	}

	public void updateRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.update(role);
	}

	public void deleteRole(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Role role = (Role) session.get(Role.class, id);
		if (role != null) {
			session.delete(role);
		}
	}

	public Role getUserRole() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Role where id = :id");
		query.setParameter("id", InitRoleId.USER);
		List<Role> list = query.list();
		if (!list.isEmpty())
			return list.get(0);
		return null;
	}

	public Role getDoctorRole() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Role where id = :id");
		query.setParameter("id", InitRoleId.DOCTOR);
		List<Role> list = query.list();
		if (!list.isEmpty())
			return list.get(0);
		return null;
	}

}
