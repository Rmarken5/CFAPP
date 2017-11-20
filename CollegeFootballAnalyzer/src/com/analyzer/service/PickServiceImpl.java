package com.analyzer.service;

import java.util.List;

import com.analyzer.dao.PickDAO;
import com.analyzer.dao.PickDAOImpl;
import com.entities.Pick;
import com.entities.Team;

public class PickServiceImpl implements PickService {

	PickDAO pickDAO = new PickDAOImpl();

	@Override
	public void insertPickIntoTable(Pick pick) {
		pickDAO.insertPickIntoTable(pick);
	}

	@Override
	public void updatePick(Pick pick) {
		pickDAO.updatePick(pick);
	}

	@Override
	public List<Pick> getPicksByWeek(Long weekNum) {

		return pickDAO.getPicksByWeek(weekNum);
	}

	@Override
	public Long getMaxWeekNum() {
		return pickDAO.getMaxWeekNum();
	}
	
	@Override
	public Pick getPickByHomeTeamAndWeek(Team homeTeam, Long weekNumber) {
		return pickDAO.getPickByHomeTeamAndWeek(homeTeam, weekNumber);
	}
}
