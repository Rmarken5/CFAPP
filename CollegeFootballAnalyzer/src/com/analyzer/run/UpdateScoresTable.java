package com.analyzer.run;

import java.util.List;

import org.apache.log4j.Logger;

import com.analyzer.bo.ScheduleBO;
import com.entities.Game;
import com.html.read.ParseHTML;
import com.html.read.ReadURL;
/**
 * 
 * This is the third step in the program. This class will record the outcomes of the games played for the previous week.
 * It will update the team table with the outright outcomes and the "against the spread" outcomes.
 * Lastly, it will update the pick table on whether or not the program picked the correct team to win
 * against the spread.
 * 
 * @author pegasus
 *
 */
public class UpdateScoresTable {
	
    static Logger log = Logger.getLogger(UpdateScoresTable.class);
    
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
			log.error("Update Scores table failed with exception...", e);
			System.exit(-99);
		}
		System.exit(0);
	}
}
