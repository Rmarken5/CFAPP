package com.analyzer.dao;

import org.hibernate.Session;

import com.analyzer.service.SessionServiceImpl;
import com.entities.GameLine;

public class GameLineDAOImpl implements GameLineDAO {

	@Override
	public void insertGameLine(GameLine gameLine) throws Exception {
		Session session = null;
		try {
			if (gameLine != null) {
				session = SessionServiceImpl.getSession();
				session.getTransaction().begin();
				session.persist(gameLine);
				session.getTransaction().commit();
			}

		} catch (Exception e) {
			throw e;
		}

	}

}
