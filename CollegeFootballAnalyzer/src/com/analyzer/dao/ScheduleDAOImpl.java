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

	
}
