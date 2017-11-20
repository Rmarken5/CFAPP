package com.analyzer.bo;

import java.util.Iterator;
import java.util.List;

import com.analyzer.service.GameLineService;
import com.analyzer.service.GameLineServiceImpl;
import com.analyzer.service.PickService;
import com.analyzer.service.PickServiceImpl;
import com.analyzer.service.ScheduleService;
import com.analyzer.service.ScheduleServiceImpl;
import com.analyzer.service.TeamService;
import com.analyzer.service.TeamServiceImpl;
import com.entities.GameLine;
import com.entities.Pick;
import com.entities.SeasonSchedule;
import com.entities.Team;
//TODO - Stage pick table with current game matchup. Find a favorite based on past matchups. 
public class PickBO {
    /**
     * This method takes the latest week loaded from the game line table
     * and inserts a row into the Pick table based on the two teams
     * previous records against the spread.
     * @throws Exception
     */
	public void predictLines() throws Exception {
		PickService pickService = new PickServiceImpl();
		GameLineService gameLineService = new GameLineServiceImpl();
		Long weekNumber = null;
		List<GameLine> gameLines = null;
		Iterator<GameLine> gameLineIter = null;
		Team homeTeam = null;
		Team awayTeam = null;
		long homeATSDifference = 0;
		long awayATSDifference = 0;
		Pick pick = null;

		try {
			weekNumber = gameLineService.getMaxWeekNum();
			if (weekNumber != null) {
				gameLines = gameLineService.getGameLineByWeek(weekNumber);
				if (gameLines != null && gameLines.size() > 0) {
					gameLineIter = gameLines.iterator();
					while (gameLineIter.hasNext()) {
						GameLine currentGameLine = gameLineIter.next();
						if (currentGameLine.getHomeTeam() != null && currentGameLine.getAwayTeam() != null) {
							pick = new Pick();
							
							homeTeam = currentGameLine.getHomeTeam();
							awayTeam = currentGameLine.getAwayTeam();
							homeATSDifference = getATSDifference(homeTeam);
							awayATSDifference = getATSDifference(awayTeam);

							if (homeATSDifference >= awayATSDifference) {
								pick.setFavoriteTeam(homeTeam);
							} else {
								pick.setFavoriteTeam(awayTeam);
							}
							pick.setAwayTeam(awayTeam);
							pick.setHomeTeam(homeTeam);
							pick.setWeekNumber(weekNumber);
							pick.setSpread(currentGameLine.getSpread());
							pickService.insertPickIntoTable(pick);
						}
					}
				}
			}

		} catch (Exception e) {
			throw e;
		}
	}

	private long getATSDifference(Team team) {
		long difference = 0;
		if (team != null) {
			difference = team.getAtsWins() - team.getAtsLosses();
		}
		return difference;
	}
	
	public void determineSpreadResults(Pick pick) throws Exception{

		PickService pickService = null;
		try{
			
			if(pick != null){
				pickService = new PickServiceImpl();
				if(pick.getHomeTeam() != null && pick.getWeekNumber() != null && pick.getAwayTeam() != null){   
				   findAgainstTheSpreadWinner(pick);
				   pickService.updatePick(pick);
				}
				
			}
			
		}catch(Exception e){
			throw e;
		}
	}

	private void findAgainstTheSpreadWinner(Pick pick)
			throws Exception {
		Team homeTeam;
		Team awayTeam;
		Long weekNumber;
		SeasonSchedule schedule;
		long awayTeamPoints;
		long homeTeamPoints;
		long resultDifference;
		double spread;
		Team atsWinner = null;
		ScheduleService scheduleService = null;
		TeamService teamService = null;
		
		
		if (pick != null) {
			scheduleService = new ScheduleServiceImpl();
			teamService = new TeamServiceImpl();
			homeTeam = pick.getHomeTeam();
			awayTeam = pick.getAwayTeam();
			weekNumber = pick.getWeekNumber();

			schedule = scheduleService.getScheduleRowByWeekHomeTeam(homeTeam, weekNumber);

			homeTeamPoints = schedule.getHomeTeamScore();
			awayTeamPoints = schedule.getAwayTeamScore();
			resultDifference = homeTeamPoints - awayTeamPoints;

			spread = pick.getSpread();

			atsWinner = findAgainstTheSpreadWinner(homeTeam, awayTeam, resultDifference, spread, atsWinner);
			if (atsWinner.equals(pick.getFavoriteTeam())) {
				pick.setIsPickedCorrectly(true);
			} else {
				pick.setIsPickedCorrectly(false);
			}
			
		/*	if(atsWinner.equals(homeTeam)){
				homeTeam.setAtsWins(homeTeam.getAtsWins() + 1);
				awayTeam.setAtsLosses(awayTeam.getAtsLosses() + 1);
			}else{
				awayTeam.setAtsWins(awayTeam.getAtsWins() + 1);
				homeTeam.setAtsLosses(homeTeam.getAtsLosses() + 1);
			}
			teamService.updateTeam(awayTeam);
			teamService.updateTeam(homeTeam);*/
		}
	}

	private Team findAgainstTheSpreadWinner(Team homeTeam, Team awayTeam, long resultDifference, double spread,
			Team atsWinner) {
		if (spread < 0) {// Home team was favored

			if (resultDifference <= 0) {
				atsWinner = awayTeam;
			} else if (resultDifference > 0 && resultDifference < Math.abs(spread)) {
				atsWinner = awayTeam;
			} else {
				atsWinner = homeTeam;
			}

		} else if (spread > 0) {// Away Team was favored.
			if (resultDifference <= 0) {
				atsWinner = awayTeam;
			} else if (resultDifference > 0 && resultDifference < Math.abs(spread)) {
				atsWinner = awayTeam;
			} else {
				atsWinner = homeTeam;
			}
		} else {// Pick'em
			if (resultDifference > 0) {
				atsWinner = homeTeam;
			} else if (resultDifference < 0) {
				atsWinner = awayTeam;
			}
		}
		return atsWinner;
	}
	
	public String pickListForWeekNumber(Long weekNumber) throws Exception{
		StringBuilder builder = null;
		List<Pick> picks = null;
		try{
			
			if(weekNumber != null){
			    picks = getPicksForWeekNumber(weekNumber);
			    if(picks != null && picks.size() > 0){
			    	builder = new StringBuilder();
			    	builder.append(String.format("|%4s||%-25s||%6s||%-25s||%-25s|", "Week", "Home Team", "Spread", "Away Team", "Favored Team"));
			    	builder.append(System.getProperty("line.separator"));
			    	builder.append("-----------------------------------------------------------------------------------------------");
			    	builder.append(System.getProperty("line.separator"));
			    	for(Pick pick : picks){
			    	    builder.append(String.format("|%4d||%-25s||%6.1f||%-25s||%-25s|", pick.getWeekNumber(), pick.getHomeTeam().getSpreadTeamName(),pick.getSpread(), pick.getAwayTeam().getSpreadTeamName(), pick.getFavoriteTeam().getSpreadTeamName()));
			    	    builder.append(System.getProperty("line.separator"));
			    	}
			    }
			}
			
		}catch(Exception e){
			throw e;
		}
		
		return builder.toString();
	}

	private List<Pick> getPicksForWeekNumber(Long weekNumber) {
		PickService pickService = null;
		List<Pick> picks = null;
		if(weekNumber != null){
			pickService = new PickServiceImpl();
			picks = pickService.getPicksByWeek(weekNumber);
			
		}
		return picks;
	}
}
