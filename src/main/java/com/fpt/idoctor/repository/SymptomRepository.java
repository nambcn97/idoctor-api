package com.fpt.idoctor.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.idoctor.model.Symptom;

@Repository
public class SymptomRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Symptom> getAllSymptom() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Symptom";
		List list = session.createQuery(hql).list();
		if (list == null)
			list = new ArrayList<>();
		return list;
	}

	public Symptom getSymptomById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Symptom) session.get(Symptom.class, id);
	}

	public void add(Symptom symptom) {
		Session session = sessionFactory.getCurrentSession();
		session.save(symptom);

	}
}
