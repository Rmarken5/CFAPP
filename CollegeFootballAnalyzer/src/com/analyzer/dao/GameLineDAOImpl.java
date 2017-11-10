package com.analyzer.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.analyzer.service.SessionServiceImpl;
import com.entities.GameLine;
import com.entities.Team;

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

	@Override
	public GameLine getByHomeTeam(Team homeTeam) throws Exception {
		GameLine gameLine = null;
		Session session = null;
		final String GET_BY_HOME_TEAM = "SELECT g FROM GameLine g where g.homeTeam.id = :id";
		Query q = null;
		try {
			if (homeTeam != null) {
				session = SessionServiceImpl.getSession();
				q = session.createQuery(GET_BY_HOME_TEAM);
				q.setParameter("id", homeTeam.getId());
				session.beginTransaction();
				gameLine = (GameLine) q.getSingleResult();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			throw e;
		}

		return gameLine;
	}
}
