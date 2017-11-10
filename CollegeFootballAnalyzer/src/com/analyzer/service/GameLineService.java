package com.analyzer.service;

import com.entities.GameLine;
import com.entities.Team;

public interface GameLineService {

	public void insertGameLine(GameLine gameLine) throws Exception;

	public GameLine getByHomeTeam(Team homeTeam) throws Exception;
}
