package com.fpt.idoctor.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.idoctor.model.EmergencyCall;

@Repository
public class EmergencyCallRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<EmergencyCall> getAllEmergencyCalls() {
		Session session = sessionFactory.getCurrentSession();
		List<EmergencyCall> listEmergencyCall = session
				.createQuery("FROM EmergencyCall").list();
		return listEmergencyCall;
	}

	public void addEmergencyCall(EmergencyCall emergencyCall) {
		Session session = sessionFactory.getCurrentSession();
		session.save(emergencyCall);
	}

	public void updateEmergencyCall(EmergencyCall emergencyCall) {
		Session session = sessionFactory.getCurrentSession();
		session.update(emergencyCall);
	}

	public void deleteEmergencyCall(Long id) {
		Session session = sessionFactory.getCurrentSession();
		EmergencyCall emergencyCall = (EmergencyCall) session
				.get(EmergencyCall.class, id);
		if (emergencyCall != null) {
			session.delete(emergencyCall);
		}
	}

	public EmergencyCall getEmergencyCall(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM EmergencyCall where id = :id");
		query.setParameter("id", id);
		List<EmergencyCall> list = query.list();
		if (!list.isEmpty())
			return list.get(0);
		return null;
	}

}
