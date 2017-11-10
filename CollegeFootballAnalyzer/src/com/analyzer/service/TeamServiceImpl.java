package com.analyzer.service;

import com.analyzer.dao.TeamDAO;
import com.analyzer.dao.TeamDAOImpl;
import com.entities.Team;

public class TeamServiceImpl implements TeamService {
	TeamDAO teamDAO = new TeamDAOImpl();

	@Override
	public Team findByScheduleName(String scheduleName) throws Exception {
		return teamDAO.findByScheduleName(scheduleName);
	}

	@Override
	public Team findByLineName(String lineName) throws Exception {
		return teamDAO.findByLineName(lineName);
	}

	@Override
	public void updateTeam(Team team) throws Exception {
		teamDAO.updateTeam(team);
	}

}
