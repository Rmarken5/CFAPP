package com.analyzer.bo;

import com.analyzer.service.TeamService;
import com.analyzer.service.TeamServiceImpl;
import com.entities.Team;

public class TeamBO {

	public void updateTeam(Team team) throws Exception {
		TeamService teamService = null;
		if (team != null) {
			teamService = new TeamServiceImpl();
			teamService.updateTeam(team);
		}
	}
}
