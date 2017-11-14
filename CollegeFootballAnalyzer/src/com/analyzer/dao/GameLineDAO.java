package com.analyzer.dao;

import java.util.List;

import com.entities.GameLine;
import com.entities.Team;

public interface GameLineDAO {

	public void insertGameLine(GameLine gameLine) throws Exception;
	
	public GameLine getByHomeTeam(Team homeTeam) throws Exception;

	public Long getMaxWeekNum() throws Exception;

	public List<GameLine> getGameLineByWeek(Long weekNumber);
}
