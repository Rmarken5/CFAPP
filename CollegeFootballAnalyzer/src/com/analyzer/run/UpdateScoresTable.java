package com.analyzer.run;

import java.util.List;

import com.analyzer.bo.ScheduleBO;
import com.entities.Game;
import com.html.read.ParseHTML;
import com.html.read.ReadURL;

public class UpdateScoresTable {

	public static void main(String[] args) {
		String html = null;
		String table = null;
		ReadURL urlReader = new ReadURL();
		ParseHTML parseHtml = new ParseHTML();
		List<Game> games = null;
		ScheduleBO scheduleBusiness = null;
		try {
			html = urlReader.getHTMLFromURL("https://www.sports-reference.com/cfb/years/2017-schedule.html");
		    if(html != null && !"".equals(html)){
		    	table = parseHtml.parseHTMLToTable(html);
		    	if(table != null && !"".equals(table)){
		    		games = parseHtml.parseTableToGames(table);
		    		if(games != null){
		    			scheduleBusiness = new ScheduleBO();
		    		    scheduleBusiness.loadGamesIntoSchedule(games);
		    		}
		    	}
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
