package com.analyzer.dao;

import java.util.List;

import com.entities.Pick;
import com.entities.Team;

public interface PickDAO {
	
	public void insertPickIntoTable(Pick pick);
	
	public void updatePick(Pick pick);
	
	public List<Pick> getPicksByWeek(Long weekNum);
	
	public Long getMaxWeekNum();
	
	public Pick getPickByHomeTeamAndWeek(Team homeTeam, Long weekNumber);
	
}
