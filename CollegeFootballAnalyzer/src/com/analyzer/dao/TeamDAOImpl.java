package com.analyzer.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.analyzer.service.SessionServiceImpl;
import com.entities.Team;

public class TeamDAOImpl implements TeamDAO {
	Session session;

	public TeamDAOImpl() {
		session = SessionServiceImpl.getSession();
	}

	@Override
	public Team findByScheduleName(String scheduleName) throws Exception {
		String queryString = "SELECT t FROM Team t WHERE t.scheduleTeamName = :scheduleName";
		Team team = null;
		try {

			if (scheduleName != null) {
				team = (Team) session.createQuery(queryString).setParameter("scheduleName", scheduleName)
						.uniqueResult();
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

	@Override
	public void updateTeam(Team team) throws Exception {
		Session session = null;
		Team existingTeam = null;
		try {
			if (team != null) {
				session = SessionServiceImpl.getSession();
				session.beginTransaction();
				existingTeam = session.find(Team.class, team.getId());
				if (existingTeam != null) {
					setTeamFields(existingTeam, team);
					session.merge(existingTeam);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void setTeamFields(Team existingTeam, Team updates) {
		if (existingTeam != null && updates != null) {
			existingTeam.setAtsLosses(updates.getAtsLosses());
			existingTeam.setAtsWins(updates.getAtsWins());
			existingTeam.setWins(updates.getWins());
			existingTeam.setLosses(updates.getLosses());
			existingTeam.setWinLossDifferenceSeason(existingTeam.getWins() - existingTeam.getLosses());
		}
	}
}
