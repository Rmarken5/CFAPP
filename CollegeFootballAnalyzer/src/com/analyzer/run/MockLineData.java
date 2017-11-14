package com.analyzer.run;

import java.util.List;

import com.analyzer.bo.GameLineBO;
import com.analyzer.bo.ScheduleBO;
import com.analyzer.bo.TeamBO;
import com.entities.Game;
import com.entities.GameLine;
import com.entities.SeasonSchedule;
import com.entities.Team;
import com.html.read.ParseHTML;
import com.html.read.ReadURL;

public class MockLineData {

	public static void main(String[] args) {
		List<SeasonSchedule> schedule = null;
		List<Game> games = null;
		String html = null;
		String table = null;
		ReadURL urlReader = new ReadURL();
		ParseHTML parseHtml = new ParseHTML();
		GameLine gameLine = null;
		TeamBO teamBO = new TeamBO();
		GameLineBO gameLineBO = new GameLineBO();
		Team homeTeam = null;
		Team awayTeam = null;
		try {
			html = urlReader.getHTMLFromURL("https://www.sports-reference.com/cfb/years/2017-schedule.html");
			if (html != null && !"".equals(html)) {
				table = parseHtml.parseHTMLToTable(html);
				if (table != null && !"".equals(table)) {
					games = parseHtml.parseTableToGames(table);

					for (Game game : games) {
						gameLine = new GameLine();
						awayTeam = teamBO.getTeamByScheduleName(game.getAwayTeam().getSchoolName());
						homeTeam = teamBO.getTeamByScheduleName(game.getHomeTeam().getSchoolName());
						if (awayTeam != null && homeTeam != null) {
							gameLine.setAwayTeam(awayTeam);
							gameLine.setHomeTeam(homeTeam);
							gameLine.setWeekNumber((long) game.getWeekNumber());
							gameLine.setSpread((Math.random() * 10) + (Math.random() * -10));
							gameLineBO.insertGameLine(gameLine);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
