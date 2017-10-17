package com.analyzer.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.analyzer.service.SessionServiceImpl;
import com.entities.Team;

public class TeamDAOImpl implements TeamDAO {
	Session session;
	public TeamDAOImpl(){
		session = SessionServiceImpl.getSession();
	}
	@Override
	public Team findByScheduleName(String scheduleName) throws Exception {
		String queryString = "SELECT t FROM Team t WHERE t.scheduleTeamName = :scheduleName";
		Team team = null;
		try {

			if (scheduleName != null) {
				team = (Team) session.createQuery(queryString).setParameter("scheduleName", scheduleName).uniqueResult();
			}
		} catch (Exception e) {
			throw e;
		}
		return team;
	}

	@Override
	public Team findByLineName(String lineName) throws Exception {
		String queryString = "SELECT t FROM Team t WHERE  t.spreadTeamName = :lineName";
		Team team = null;
		try {

			if (lineName != null) {
				team = (Team) session.createQuery(queryString).setParameter("lineName", lineName).uniqueResult();
			}
		} catch (Exception e) {
			throw e;
		}
		return team;
	}

}
