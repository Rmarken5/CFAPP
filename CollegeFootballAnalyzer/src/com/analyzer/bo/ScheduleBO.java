package com.analyzer.bo;

import java.util.Iterator;
import java.util.List;

import com.analyzer.service.ScheduleService;
import com.analyzer.service.ScheduleServiceImpl;
import com.analyzer.service.TeamService;
import com.analyzer.service.TeamServiceImpl;
import com.entities.Game;
import com.entities.SeasonSchedule;
import com.entities.Team;

public class ScheduleBO {
	
	public void loadGamesIntoSchedule(List<Game> games) throws Exception {
		Team team = null;
		Iterator<Game> gameIter = null;
		SeasonSchedule schedule = null;
		TeamService ts = new TeamServiceImpl();
		ScheduleService scheduleService = new ScheduleServiceImpl();
		try {
			if (games != null && games.size() > 0) {

				gameIter = games.iterator();
				while (gameIter.hasNext()) {
					Game game = gameIter.next();
					schedule = new SeasonSchedule();
					schedule.setWeekNumber((long) game.getWeekNumber());
					schedule.setHomeTeamScore((long) game.getHomeTeam().getPoints());
					schedule.setAwayTeamScore((long) game.getAwayTeam().getPoints());
					schedule.setGameTimestamp(game.getDateGame());
					schedule.setWeekNumber((long) game.getWeekNumber());

					team = ts.findByScheduleName(game.getHomeTeam().getSchoolName().trim());
					if (team != null) {
						schedule.setHomeTeam(team);
						team = ts.findByScheduleName(game.getAwayTeam().getSchoolName().trim());
						schedule.setAwayTeam(team);
						if (team != null) {
							team = ts.findByScheduleName(game.getWinner().getSchoolName().trim());
							if (team != null) {
								schedule.setWinningTeam(team);
								scheduleService.insertIntoSchedule(schedule);
							}else{
								System.out.println("Missing Team: " + game.getWinner().getSchoolName());
							}
						} else {
							System.out.println("Missing Team: " + game.getAwayTeam().getSchoolName());
						}
					} else {
						System.out.println("Missing Team: " + game.getHomeTeam().getSchoolName());
					}
				}
				
			}
		} catch (Exception e) {
			throw e;
		}

	}

}
