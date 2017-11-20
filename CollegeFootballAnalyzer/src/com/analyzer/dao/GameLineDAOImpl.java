package com.analyzer.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public GameLine getByHomeTeam(Team homeTeam, Long weekNumber) throws Exception {
		GameLine gameLine = null;
		Session session = null;
		final String GET_BY_HOME_TEAM = "SELECT g FROM GameLine g where g.homeTeam.id = :id AND g.weekNumber = :weekNum";
		Query q = null;
		//TODO - Make results for current week.
		try {
			if (homeTeam != null && weekNumber != null) {
				System.out.println(homeTeam.getSpreadTeamName());
				session = SessionServiceImpl.getSession();
				q = session.createQuery(GET_BY_HOME_TEAM);
				q.setParameter("id", homeTeam.getId());
				q.setParameter("weekNum", weekNumber);
				session.beginTransaction();
				gameLine = (GameLine) q.getSingleResult();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			System.out.println(homeTeam.toString() + " Week number: " + weekNumber);
			throw e;
		}

		return gameLine;
	}

	@Override
	public Long getMaxWeekNum() throws Exception {
        Long weekNumber = null;
        final String GET_MAX_WEEK = "SELECT MAX(gl.weekNumber) FROM GameLine gl";
        Session session = null;
        Query q = null;
        try{
        	session = SessionServiceImpl.getSession();
        	q = session.createQuery(GET_MAX_WEEK, Long.class);
        	session.beginTransaction();
        	weekNumber = (Long)q.getSingleResult();
        	session.getTransaction().commit();
        }catch(Exception e){
        	throw e;
        }
		
		return weekNumber;
	}

	
	@Override
	public List<GameLine> getGameLineByWeek(Long weekNumber) {
        Map<String,Object> parameters = null;
        List<GameLine> results = null;
        Session session = null;
        Query q = null;
        final String GET_LINE_BY_WEEK = "SELECT gl FROM GameLine gl where gl.weekNumber = :weekNum";
        try{
        	if(weekNumber != null){
        		parameters = new HashMap<String,Object>();
        		parameters.put("weekNum", weekNumber);
        		session = SessionServiceImpl.getSession();
        		q = session.createQuery(GET_LINE_BY_WEEK, GameLine.class);
        		q.setParameter("weekNum", weekNumber);
        		session.beginTransaction();
        		results = q.getResultList();
        		session.getTransaction().commit();
        	}
        }catch(Exception e){
        	throw e;
        }
		
		return results;
	
	}
	
}
