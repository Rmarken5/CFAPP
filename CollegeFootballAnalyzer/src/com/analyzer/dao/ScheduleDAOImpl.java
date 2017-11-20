package com.analyzer.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.analyzer.service.ScheduleService;
import com.analyzer.service.SessionServiceImpl;
import com.analyzer.service.TeamService;
import com.analyzer.service.TeamServiceImpl;
import com.entities.Team;
import com.entities.Game;
import com.entities.Schedule;
import com.entities.SeasonSchedule;

public class ScheduleDAOImpl implements ScheduleDAO {

	@Override
	public void insertIntoSchedule(SeasonSchedule schedule) throws Exception {
		
		Session session = SessionServiceImpl.getSession();
		try{
		if(schedule != null){
            session.getTransaction().begin();
            session.save(schedule);
            session.getTransaction().commit();
		}
		}catch(Exception e){
		   System.out.println( schedule.toString());
			e.printStackTrace();
		}
	}

	@Override
	public Long getLatestWeekNum() throws Exception {
		Long weekNumber = null;
		Session session = SessionServiceImpl.getSession();
		String query = "SELECT MAX(s.weekNumber) FROM SeasonSchedule s";
		
		session.getTransaction().begin();
		weekNumber = session.createQuery(query, Long.class).getSingleResult();
		session.getTransaction().commit();
		return weekNumber;
		
	}
	
	@Override
	public SeasonSchedule getScheduleRowByWeekHomeTeam(Team homeTeam, Long weekNumber) throws Exception {
		SeasonSchedule schedule = null;
		Session session = null;
		final String query = "SELECT s FROM SeasonSchedule s WHERE s.homeTeam = :homeTeam AND s.weekNumber = :weekNumber";
		Query q = null;
		try{
			if(homeTeam != null && weekNumber != null){
				session = SessionServiceImpl.getSession();
				session.beginTransaction();
				q = session.createQuery(query, SeasonSchedule.class);
				q.setParameter("homeTeam", homeTeam);
				q.setParameter("weekNumber", weekNumber);
				schedule = (SeasonSchedule) q.getSingleResult();
				session.getTransaction().commit();
			}
		}catch(Exception e){
            System.out.println(homeTeam.toString() + " week number: " + weekNumber);
             
			throw e;
		}
		
		return schedule;
	}

	
}
