package com.analyzer.dao;

import java.util.List;

import com.entities.Game;
import com.entities.Schedule;
import com.entities.SeasonSchedule;

public interface ScheduleDAO {
	
	public void insertIntoSchedule(SeasonSchedule schedule) throws Exception; 
	public Long getLatestWeekNum() throws Exception;
}
