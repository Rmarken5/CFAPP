package com.analyzer.bo;

import java.util.Iterator;
import java.util.List;

import com.analyzer.service.PickService;
import com.analyzer.service.PickServiceImpl;
import com.analyzer.service.ScheduleService;
import com.analyzer.service.ScheduleServiceImpl;
import com.analyzer.service.TeamService;
import com.analyzer.service.TeamServiceImpl;
import com.entities.Game;
import com.entities.GameLine;
import com.entities.Pick;
import com.entities.SeasonSchedule;
import com.entities.Team;

public class ScheduleBO {
    /**
     * Loads the Schedule table with the current weeks games.
     * This is to be invoked after the game line table has been populated
     * so that picks can be derived from this weeks matchup.
     * @param games
     * @throws Exception
     */
	public void loadGamesIntoSchedule(List<Game> games) throws Exception {
		Team homeTeam = null;
		Team awayTeam = null;
		Team winningTeam = null;
		Iterator<Game> gameIter = null;
		SeasonSchedule schedule = null;
		TeamService ts = new TeamServiceImpl();
		ScheduleService scheduleService = new ScheduleServiceImpl();
	GameLine gameLine = null;
		PickService pickService = null;
		Pick pick = null;
		PickBO pickBO = null;
		try {
			if (games != null && games.size() > 0) {
                pickService = new PickServiceImpl();
                pickBO = new PickBO();
				gameIter = games.iterator();
				while (gameIter.hasNext()) {
					Game game = gameIter.next();
					schedule = new SeasonSchedule();
					schedule.setWeekNumber((long) game.getWeekNumber());
					schedule.setHomeTeamScore((long) game.getHomeTeam().getPoints());
					schedule.setAwayTeamScore((long) game.getAwayTeam().getPoints());
					schedule.setGameTimestamp(game.getDateGame());
					schedule.setWeekNumber((long) game.getWeekNumber());

					homeTeam = ts.findByScheduleName(game.getHomeTeam().getSchoolName().trim());
					if (homeTeam != null) {
						schedule.setHomeTeam(homeTeam);
						awayTeam = ts.findByScheduleName(game.getAwayTeam().getSchoolName().trim());
						schedule.setAwayTeam(awayTeam);
						if (awayTeam != null) {
							winningTeam = ts.findByScheduleName(game.getWinner().getSchoolName().trim());
							if (winningTeam != null) {
								
								schedule.setWinningTeam(winningTeam);
								
								scheduleService.insertIntoSchedule(schedule);
								
								calculateWinsLoss(homeTeam, awayTeam, winningTeam);
								
								gameLine = getGameLineByHomeTeam(homeTeam, (long)game.getWeekNumber());
								
								calculateAgainstTheSpread(homeTeam, awayTeam, gameLine, game);
								
								updateTeam(homeTeam);
								updateTeam(awayTeam);
								
								pick = pickService.getPickByHomeTeamAndWeek(homeTeam, schedule.getWeekNumber());
							
								pickBO.determineSpreadResults(pick);
							
							} else {
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

	private void calculateWinsLoss(Team homeTeam, Team awayTeam, Team winningTeam) {
		if (winningTeam.getId() == homeTeam.getId()) {
			homeTeam.setWins(homeTeam.getWins() + 1);
			awayTeam.setLosses(awayTeam.getLosses() + 1);
		} else {
			homeTeam.setWins(homeTeam.getWins() + 1);
			awayTeam.setLosses(awayTeam.getLosses() + 1);
		}
	}

	private void calculateAgainstTheSpread(Team homeTeam, Team awayTeam, GameLine gameLine, Game game) {
		double difference;
		difference = game.getHomeTeam().getPoints() - game.getAwayTeam().getPoints();
		if (gameLine.getSpread() < 0) {

			if (difference > Math.abs(gameLine.getSpread())) {
				homeTeam.setAtsWins(homeTeam.getAtsWins() + 1);
				awayTeam.setAtsLosses(awayTeam.getAtsLosses() + 1);
			} else if (difference < Math.abs(gameLine.getSpread())) {
				awayTeam.setAtsWins(awayTeam.getAtsWins() + 1);
				homeTeam.setAtsLosses(homeTeam.getAtsLosses() + 1);
			}
		} else if (gameLine.getSpread() > 0) {
			
			if (difference > 0 || Math.abs(difference) < gameLine.getSpread()) {
				homeTeam.setAtsWins(homeTeam.getAtsWins() + 1);
				awayTeam.setAtsLosses(awayTeam.getAtsLosses() + 1);
			} else if (Math.abs(difference) > gameLine.getSpread()) {
				homeTeam.setAtsLosses(homeTeam.getAtsLosses() + 1);
				awayTeam.setAtsWins(awayTeam.getAtsWins() + 1);
			}
		}
	}

	private void updateTeam(Team team) throws Exception {
		TeamBO teamBO= null;
		if (team != null) {
			teamBO = new TeamBO();
			teamBO.updateTeam(team);
		}
	}

	private GameLine getGameLineByHomeTeam(Team homeTeam, Long weekNumber) throws Exception {
		GameLineBO gameLineService = null;
		GameLine gameLine = null;
		if (homeTeam != null) {
			gameLineService = new GameLineBO();
			gameLine = gameLineService.getSpreadByHomeTeam(homeTeam, weekNumber);
		}
		return gameLine;
	}
}
