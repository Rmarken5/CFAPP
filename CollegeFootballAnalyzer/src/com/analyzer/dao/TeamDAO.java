package com.analyzer.dao;

import com.entities.Team;

public interface TeamDAO {

    public Team findByScheduleName(String scheduleName) throws Exception;
    public Team findByLineName(String lineName)throws Exception;
    public void updateTeam(Team team) throws Exception;
    
}
