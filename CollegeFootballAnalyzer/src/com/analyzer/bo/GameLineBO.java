package com.analyzer.bo;

import java.util.Iterator;
import java.util.List;

import com.analyzer.service.GameLineService;
import com.analyzer.service.GameLineServiceImpl;
import com.analyzer.service.ScheduleService;
import com.analyzer.service.ScheduleServiceImpl;
import com.analyzer.service.TeamService;
import com.analyzer.service.TeamServiceImpl;
import com.entities.Date;
import com.entities.Event;
import com.entities.EventType;
import com.entities.GameLine;
import com.entities.Schedule;
import com.entities.Team;

public class GameLineBO {

	public void loadLines(Schedule schedule) throws Exception {
		TeamService tService = null;
		ScheduleService sService = null;
		GameLineService gameService = null;
		GameLine gameLine = null;
		Long weekNumber = null;
		Team homeTeam = null;
		Team awayTeam = null;
		Double spread = null;
		String spreadString = null;
		EventType eventType = null;
		List<Date> dates = null;
		Date date = null;
		List<Event> events = null;
		Event event = null;
		try {
			if (schedule != null) {
				if (schedule.getEventType() != null) {
					eventType = schedule.getEventType();
					if (eventType.getDates() != null) {
						dates = eventType.getDates();
						sService = new ScheduleServiceImpl();
						gameService = new GameLineServiceImpl();
						weekNumber = sService.getLatestWeekNum() + 1L;
						for (Iterator<Date> dateIter = dates.iterator(); dateIter.hasNext();) {
							date = dateIter.next();

							if (date.getEvents() != null) {

								events = date.getEvents();
								for (Iterator<Event> eventIter = events.iterator(); eventIter.hasNext();) {

									event = eventIter.next();
									gameLine = new GameLine();

									if (event.getCompetitorOne() != null && event.getCompetitorTwo() != null) {

										tService = new TeamServiceImpl();

										if (event.getName()
												.substring(event.getName().indexOf("@") + 1, event.getName().length())
												.trim().equalsIgnoreCase(event.getCompetitorOne().getName())) {

											homeTeam = tService
													.findByLineName(event.getCompetitorOne().getName().trim());
											awayTeam = tService
													.findByLineName(event.getCompetitorTwo().getName().trim());

											if (event.getCompetitorOne().getLine() != null
													&& event.getCompetitorOne().getLine().getChoice() != null
													&& event.getCompetitorOne().getLine().getChoice()
															.getNumber() != null) {

												spreadString = event.getCompetitorOne().getLine().getChoice()
														.getNumber();
											}
										} else {

											awayTeam = tService
													.findByLineName(event.getCompetitorOne().getName().trim());
											homeTeam = tService
													.findByLineName(event.getCompetitorTwo().getName().trim());
											if (event.getCompetitorTwo().getLine() != null
													&& event.getCompetitorTwo().getLine().getChoice() != null
													&& event.getCompetitorTwo().getLine().getChoice()
															.getNumber() != null) {

												spreadString = event.getCompetitorTwo().getLine().getChoice()
														.getNumber();
											}
										}

										if (homeTeam != null && awayTeam != null) {

											gameLine.setHomeTeam(homeTeam);
											gameLine.setAwayTeam(awayTeam);
											if (spreadString.contains("½")) {

												spreadString = spreadString.substring(0, spreadString.indexOf("½"));
												spread = Double.parseDouble(spreadString);

												if (spread < 0) {
													spread -= .5;
												} else {
													spread += .5;
												}
											} else {

												spread = Double.parseDouble(spreadString);
											}
											gameLine.setSpread(spread);
											gameLine.setWeekNumber(weekNumber);
											gameService.insertGameLine(gameLine);
										} else {
											System.out.println("Not in schedule: " + event.getCompetitorOne().getName()
													+ " or " + event.getCompetitorTwo().getName());
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void loadLines(List<Schedule> schedule) throws Exception {
		Iterator<Schedule> scheduleIter = null;
		try {
			if (schedule != null) {
				scheduleIter = schedule.iterator();
				while (scheduleIter.hasNext()) {
					Schedule current = scheduleIter.next();
					loadLines(current);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public GameLine getSpreadByHomeTeam(Team homeTeam) throws Exception{
		GameLineService gameLineService = null;
		GameLine gameLine = null;
		try{
			if(homeTeam != null){
				gameLineService = new GameLineServiceImpl();
				gameLine = gameLineService.getByHomeTeam(homeTeam);
			}
		}catch(Exception e){
			throw e;
		}
		return gameLine;
	}
}
