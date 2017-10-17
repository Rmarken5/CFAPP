package com.analyzer.dao;

import java.util.Iterator;
import java.util.List;

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
		if(schedule != null){
            session.getTransaction().begin();
            session.save(schedule);
            session.getTransaction().commit();
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

	
}
