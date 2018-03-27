package com.fpt.idoctor.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.idoctor.model.Specialty;

@Repository
public class SpecialtyRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Specialty> getAllSpecialty() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Specialty";
		List list = session.createQuery(hql).list();
		if (list == null)
			list = new ArrayList<>();
		return list;
	}

	public Specialty getSpecialtyById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Specialty) session.get(Specialty.class, id);
	}

	public void add(Specialty specialty) {
		Session session = sessionFactory.getCurrentSession();
		session.save(specialty);

	}
}
