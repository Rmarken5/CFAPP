package com.analyzer.service;

import com.analyzer.dao.GameLineDAO;
import com.analyzer.dao.GameLineDAOImpl;
import com.entities.GameLine;

public class GameLineServiceImpl implements GameLineService {

	GameLineDAO gameLineDAO = new GameLineDAOImpl();

	@Override
	public void insertGameLine(GameLine gameLine) throws Exception {
		gameLineDAO.insertGameLine(gameLine);
	}

}
