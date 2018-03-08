package com.fpt.idoctor.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.catalina.security.SecurityUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.fpt.idoctor.model.User;
import com.fpt.idoctor.security.SecurityUtils;

@Repository
@Transactional
public class TokenSessionRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void initTokenStore() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "create table if not exists oauth_access_token ( "
				+ "token_id VARCHAR(255), "
				+ "token LONG VARBINARY, "
				+ "authentication_id VARCHAR(255) PRIMARY KEY, "
				+ "user_name VARCHAR(255), "
				+ "client_id VARCHAR(255), "
				+ "authentication LONG VARBINARY, "
				+ "refresh_token VARCHAR(255)) DEFAULT CHARSET=utf8 ;";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
		
		String sql2 = "create table if not exists oauth_refresh_token ( "
				+ "token_id VARCHAR(255), "
				+ "token LONG VARBINARY, "
				+ "authentication LONG VARBINARY ) DEFAULT CHARSET=utf8 ;";
		SQLQuery query2 = session.createSQLQuery(sql2);
		query2.executeUpdate();
	}
	
//	public List<TokenSession> getAllTokenSession() {
//		List<TokenSession> tokenSessions = null;
//		Session session = sessionFactory.getCurrentSession();
//		tokenSessions = session.createQuery("FROM TokenSession").list();
//		return tokenSessions;
//	}

//	public void addTokenSession(TokenSession tokenSession) {
//		Session session = sessionFactory.getCurrentSession();
//		session.save(tokenSession);
//	}

//	public void updateTokenSession(TokenSession tokenSession) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(tokenSession);
//	}

//	public void deleteTokenSession(Long id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		TokenSession tokenSession = (TokenSession) session.load(TokenSession.class, id);
//		if (tokenSession != null) {
//			session.delete(tokenSession);
//		}
//	}
	public boolean isExistToken(String token) {
//		Session session = this.sessionFactory.getCurrentSession();
//		Query query = session.createQuery("FROM TokenSession WHERE token = :token");
//		query.setParameter("token", token);
//		query.setMaxResults(1);
//		boolean isExist = ! query.list().isEmpty();
//		return isExist;
		return true;
	}
	public User getUser(String token) {
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("FROM TokenSession WHERE token = :token");
//		query.setParameter("token", token);
//		query.setMaxResults(1);
//		List<TokenSession> list = query.list();
//		if(! list.isEmpty()) {
//			return list.get(0).getUser();
//		}
//		return null;
		return SecurityUtils.getCurrentUser();
	}

}
