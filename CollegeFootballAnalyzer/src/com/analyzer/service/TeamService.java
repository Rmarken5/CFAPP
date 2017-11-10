package com.analyzer.service;

import com.entities.Team;

public interface TeamService {

    public Team findByScheduleName(String scheduleName) throws Exception;
    public Team findByLineName(String lineName)throws Exception;
    public void updateTeam(Team team) throws Exception;

}
