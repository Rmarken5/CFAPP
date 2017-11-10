package com.html.read;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.entities.Game;
import com.entities.Opponent;
import com.html.read.utilities.Constants;
import com.html.read.utilities.DateUtility;

public class ParseHTML {
//TODO - Create Team BO and update team record from last week schedule.
//TODO - Create a process that updates Team record and against the spread and Pick table correction
	//TODO - create a process that pulls game results and compares to spread of that week.
	public String parseHTMLToTable(String html) throws Exception {
		StringBuilder tableBody = null;
		BufferedReader reader = null;
		boolean bodySwitch = false;
		try {
			if (html != null && !"".equals(html)) {
				reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(html.getBytes())));
				String temp = null;
				if (reader != null) {
					tableBody = new StringBuilder();
					while ((temp = reader.readLine()) != null) {
						if (temp.contains("<tbody>")) {
							bodySwitch = true;
						} else if (temp.contains("</tbody>")) {
							break;
							
						}
						if (bodySwitch && temp.contains("<tr") &&  !temp.contains("thead")) {//"<tr class=\"ranked\""
							tableBody.append(temp).append(System.lineSeparator());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return tableBody.toString();
	}

	public List<Game> parseTableToGames(String table) throws Exception {
		List<Game> results = null;
		Game currentGame = null;
		Opponent winner = null;
		Opponent loser = null;
		String[] rows = null;
		String[] cells = null;
		String dataStat = null;
		try {
			if (table != null && !"".equals(table)) {
				results = new ArrayList<Game>();
				rows = table.split("<tr ");
				if (rows != null && rows.length > 0) {

					for (String row : rows) {
						if(row != null && !"".equals(row.trim())){
						currentGame = new Game();
						winner = new Opponent();
						loser = new Opponent();
						cells = row.split("<td ");
						String dateGame = null;
						String ranker = null;
						String weekNumber = null;
						String timeGame = null;
						String loserPoints = null;
						String loserTeamName = null;
						String winnerPoints = null;
						String winnerTeamName = null;
						String notes = null;
						for (String cell : cells) {
							if (cell != null && !"".equals(cell)) {
								
								
								//System.out.println(cell + System.lineSeparator());
								dataStat = cell.substring(cell.indexOf("data-stat=\""));
								dataStat = dataStat.substring(dataStat.indexOf('"') + 1,
										StringUtils.ordinalIndexOf(dataStat, "\"", 2));
								//System.out.println(dataStat);
								switch (dataStat) {
								case Constants.RANKER:
									ranker = cell.substring(StringUtils.ordinalIndexOf(cell, ">", 2) + 1,
											StringUtils.ordinalIndexOf(cell, "<", 2));
									currentGame.setId(Long.valueOf(ranker));
									break;
								case Constants.BROADCASTER:
									break;
								case Constants.DATE_GAME:
									if(cell.contains("<a")){
										dateGame = cell.substring(StringUtils.ordinalIndexOf(cell, ">", 2)+1, cell.indexOf("</a>"));
									}else{
										dateGame =  cell.substring(cell.indexOf(">")+1, cell.indexOf("<"));
									}
									break;
								case Constants.DAY_NAME:
									break;
								case Constants.GAME_LOCATION:
									break;
								case Constants.LOSER_POINTS:
									loserPoints = cell.substring(cell.indexOf(">")+1, cell.indexOf("<"));
									if(loserPoints != null && !"".equals(loserPoints)){
									    loser.setPoints(Integer.valueOf(loserPoints));
									}
									break;
								case Constants.LOSER_SCHOOL_NAME:
									if(cell.contains("<a")){
										 loserTeamName = cell.substring(StringUtils.ordinalIndexOf(cell, ">", 2)+1, cell.indexOf("</a>"));
									}else{
									    loserTeamName =  cell.substring(cell.indexOf(">")+1, cell.indexOf("<"));
									}
									    loser.setSchoolName(loserTeamName);
									break;
								case Constants.NOTES:
									notes = cell.substring(cell.indexOf(">")+1, cell.indexOf("<"));
									currentGame.setNotes(notes);
									break;
								case Constants.TIME_GAME:
									timeGame = cell.substring(cell.indexOf(">")+1, cell.indexOf("<"));
									break;
								case Constants.WEEK_NUMBER:
									weekNumber = cell.substring(cell.indexOf(">")+1, cell.indexOf("<"));
									currentGame.setWeekNumber(Integer.valueOf(weekNumber));
									break;
								case Constants.WINNER_POINTS:
									winnerPoints = cell.substring(cell.indexOf(">")+1, cell.indexOf("<"));
									if(winnerPoints != null && !"".equals(winnerPoints)){
										winner.setPoints(Integer.valueOf(winnerPoints));
									}
									
									break;
								case Constants.WINNER_SCHOOL_NAME:
									if(cell.contains("<a")){
										winnerTeamName = cell.substring(StringUtils.ordinalIndexOf(cell, ">", 2)+1, cell.indexOf("</a>"));
									}else{
										winnerTeamName =  cell.substring(cell.indexOf(">")+1, cell.indexOf("<"));
									}
									winner.setSchoolName(winnerTeamName);
									break;
									default:
										break;
								}
								
							}
						}
						Timestamp ts = DateUtility.formTimestampFromDateTime(dateGame, timeGame);
						if(DateUtility.isDateBetweenLastWedToThis(new Date(ts.getTime()))){
						    currentGame.setDateGame(ts);
						    currentGame.setLoser(loser);
						    currentGame.setWinner(winner);
						    if(row.contains("@")){
								currentGame.setHomeTeam(currentGame.getLoser());
								currentGame.setAwayTeam(currentGame.getWinner());
							}else{
								currentGame.setHomeTeam(currentGame.getWinner());
								currentGame.setAwayTeam(currentGame.getLoser());
							}
						    results.add(currentGame);
					    }
						
					}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return results;
	}
}
