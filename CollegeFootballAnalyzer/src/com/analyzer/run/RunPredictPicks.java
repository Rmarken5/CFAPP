package com.analyzer.run;

import java.util.Date;

import org.apache.log4j.Logger;

import com.analyzer.bo.PickBO;
import com.analyzer.service.GameLineService;
import com.analyzer.service.GameLineServiceImpl;
import com.email.communication.Mailer;
/**
 * This class loads the pick table with the systems predictions. This is the second step to run
 * after the game line table has been populated.
 * @author pegasus
 *
 */
public class RunPredictPicks {

	static Logger log = Logger.getLogger(RunPredictPicks.class);
	
	public static void main(String[] args) {
		PickBO pickBO = new PickBO();
		String picks = null;
		GameLineService gameService = new GameLineServiceImpl();
		try{
			pickBO.predictLines();
			picks =  pickBO.pickListForWeekNumber(gameService.getMaxWeekNum());
			if(picks != null) {
				Mailer.send("send.markenapps@gmail.com","Zeppelin32!","marken.ryan@gmail.com","Date: " + new Date().toString() + " Lines.", picks);
			}
	    	
	    	log.info("Execution Complete");
		}catch(Exception e){
			e.printStackTrace();
		}
		System.exit(0);
	}
}
