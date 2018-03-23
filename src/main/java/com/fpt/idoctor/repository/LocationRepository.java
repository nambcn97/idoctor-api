package com.fpt.idoctor.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.idoctor.model.Location;
@Repository
public class LocationRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public List<Location> getAllLocations() {
		Session session = sessionFactory.getCurrentSession();
		List<Location> listLocation = session.createQuery("FROM Location")
				.list();
		return listLocation;
	}

	public void addLocation(Location location) {
		Session session = sessionFactory.getCurrentSession();
		session.save(location);
	}

	public void updateLocation(Location location) {
		Session session = sessionFactory.getCurrentSession();
		session.update(location);
	}

	public void deleteLocation(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Location location = (Location) session.get(Location.class, id);
		if (location != null) {
			session.delete(location);
		}
	}

	public Location getLocation(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Location where id = :id");
		query.setParameter("id", id);
		List<Location> list = query.list();
		if (!list.isEmpty())
			return list.get(0);
		return null;
	}

}
