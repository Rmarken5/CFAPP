package com.analyzer.service;

import com.analyzer.dao.ScheduleDAO;
import com.analyzer.dao.ScheduleDAOImpl;
import com.entities.SeasonSchedule;
import com.entities.Team;

public class ScheduleServiceImpl implements ScheduleService {

	
	ScheduleDAO scheduleDAO = new ScheduleDAOImpl();

	@Override
	public void insertIntoSchedule(SeasonSchedule schedule) throws Exception {
		scheduleDAO.insertIntoSchedule(schedule);
	}

	@Override
	public Long getLatestWeekNum() throws Exception {

		return scheduleDAO.getLatestWeekNum();
	
	}
 
	@Override
	public SeasonSchedule getScheduleRowByWeekHomeTeam(Team homeTeam, Long weekNumber) throws Exception {

		return scheduleDAO.getScheduleRowByWeekHomeTeam(homeTeam, weekNumber);
	
	}

}
