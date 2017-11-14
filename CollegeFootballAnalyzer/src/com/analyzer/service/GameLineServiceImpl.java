package com.analyzer.service;

import java.util.List;

import com.analyzer.dao.GameLineDAO;
import com.analyzer.dao.GameLineDAOImpl;
import com.entities.GameLine;
import com.entities.Team;

public class GameLineServiceImpl implements GameLineService {

	GameLineDAO gameLineDAO = new GameLineDAOImpl();

	@Override
	public void insertGameLine(GameLine gameLine) throws Exception {
		gameLineDAO.insertGameLine(gameLine);
	}

	@Override
	public GameLine getByHomeTeam(Team homeTeam) throws Exception {
		
		return gameLineDAO.getByHomeTeam(homeTeam);
	}
	
	@Override
	public Long getMaxWeekNum() throws Exception {

		return gameLineDAO.getMaxWeekNum();
	}

	@Override
	public List<GameLine> getGameLineByWeek(Long weekNumber) throws Exception {
		
		return gameLineDAO.getGameLineByWeek(weekNumber);
	
	}
}
