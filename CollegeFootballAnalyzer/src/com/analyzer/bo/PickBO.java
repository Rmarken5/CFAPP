package com.analyzer.bo;

import java.util.Iterator;
import java.util.List;

import com.analyzer.service.GameLineService;
import com.analyzer.service.GameLineServiceImpl;
import com.analyzer.service.PickService;
import com.analyzer.service.PickServiceImpl;
import com.entities.GameLine;
import com.entities.Pick;
import com.entities.Schedule;
import com.entities.Team;
import com.xml.read.ParseLines;
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
		List<Pick> currentWeekPicks = null;
		Long weekNumber = null;
		Iterator<Pick> pickIter = null;
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
	
}
