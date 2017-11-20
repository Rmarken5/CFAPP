package com.analyzer.service;

import com.entities.Schedule;
import com.entities.SeasonSchedule;
import com.entities.Team;

public interface ScheduleService {

	public void insertIntoSchedule(SeasonSchedule schedule) throws Exception; 
	public Long getLatestWeekNum() throws Exception;
	public SeasonSchedule getScheduleRowByWeekHomeTeam(Team homeTeam, Long weekNumber) throws Exception;	
}
