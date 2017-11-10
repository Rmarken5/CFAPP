package com.analyzer.bo;

import java.util.Iterator;
import java.util.List;

import com.analyzer.service.PickService;
import com.analyzer.service.PickServiceImpl;
import com.entities.Pick;
import com.entities.Team;

public class PickBO {

	public void predictLines() throws Exception {
		PickService pickService = new PickServiceImpl();
		List<Pick> currentWeekPicks = null;
		Long weekNumber = null;
		Iterator<Pick> pickIter = null;
		Team homeTeam = null;
		Team awayTeam = null;
		long homeATSDifference = 0;
		long awayATSDifference = 0;

		try {
			weekNumber = pickService.getMaxWeekNum();
			if (weekNumber != null) {
				currentWeekPicks = pickService.getPicksByWeek(weekNumber);
				if (currentWeekPicks != null && currentWeekPicks.size() > 0) {
					pickIter = currentWeekPicks.iterator();
					while (pickIter.hasNext()) {
						Pick pick = pickIter.next();
						if (pick.getHomeTeam() != null && pick.getAwayTeam() != null) {
							homeTeam = pick.getHomeTeam();
							awayTeam = pick.getAwayTeam();
							homeATSDifference = getATSDifference(homeTeam);
							awayATSDifference = getATSDifference(awayTeam);

							if (homeATSDifference >= awayATSDifference) {
								pick.setFavoriteTeam(homeTeam);
							} else {
								pick.setFavoriteTeam(awayTeam);
							}
							pickService.updatePick(pick);
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
