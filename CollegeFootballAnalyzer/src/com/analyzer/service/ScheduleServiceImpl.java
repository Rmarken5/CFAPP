package com.analyzer.service;

import com.analyzer.dao.ScheduleDAO;
import com.analyzer.dao.ScheduleDAOImpl;
import com.entities.SeasonSchedule;

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


}
