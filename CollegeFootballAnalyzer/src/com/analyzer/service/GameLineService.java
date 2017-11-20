package com.analyzer.service;

import java.util.List;

import com.entities.GameLine;
import com.entities.Team;

public interface GameLineService {

	public void insertGameLine(GameLine gameLine) throws Exception;

	public GameLine getByHomeTeam(Team homeTeam, Long weekNumber) throws Exception;
	
	public Long getMaxWeekNum() throws Exception;
	
	public List<GameLine> getGameLineByWeek(Long weekNumber) throws Exception;
	
}
