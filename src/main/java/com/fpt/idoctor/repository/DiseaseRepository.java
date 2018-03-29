package com.fpt.idoctor.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiseaseRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Long> findBySymptom(String symptom, Long symptomId) {
		if (symptom == null)
			symptom = "";
		String hql = "SELECT DISTINCT specialty.id as specialtyId FROM Disease WHERE LOWER(detail) like :symptom OR LOWER(overview) like :symptom ";
		if (symptomId != null) {
			hql += "OR symptom.id = :symptomId";
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("symptom", "%" + symptom.toLowerCase().trim() + "%");
		if (symptomId != null) {
			query.setParameter("symptomId", symptomId);
		}
		List list = query.list();
		if (list == null)
			return new ArrayList<Long>();
		return list;
	}
}
