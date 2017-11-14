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
	
	public Team getTeamByLineName(String lineName) throws Exception{
		TeamService teamService = null;
		if(lineName != null && !"".equals(lineName)){
			teamService = new TeamServiceImpl();
			return teamService.findByLineName(lineName);
		}
		return null;
	}
	
	public Team getTeamByScheduleName(String scheduleName) throws Exception{
		TeamService teamService = null;
		if(scheduleName != null && !"".equals(scheduleName)){
			teamService = new TeamServiceImpl();
			return teamService.findByScheduleName(scheduleName);
		}
		return null;
	}
}
