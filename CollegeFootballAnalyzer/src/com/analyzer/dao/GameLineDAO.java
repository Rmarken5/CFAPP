package com.analyzer.dao;

import com.entities.GameLine;
import com.entities.Team;

public interface GameLineDAO {

	public void insertGameLine(GameLine gameLine) throws Exception;
	
	public GameLine getByHomeTeam(Team homeTeam) throws Exception;
}
