package com.analyzer.dao;

import java.util.List;

import com.entities.Game;
import com.entities.Schedule;
import com.entities.SeasonSchedule;
import com.entities.Team;

public interface ScheduleDAO {
	
	public void insertIntoSchedule(SeasonSchedule schedule) throws Exception; 
	
	public Long getLatestWeekNum() throws Exception;
	
	public SeasonSchedule getScheduleRowByWeekHomeTeam(Team homeTeam, Long weekNumber) throws Exception;	
}
