package com.analyzer.run;

import org.apache.log4j.Logger;

import com.analyzer.bo.GameLineBO;
import com.entities.Schedule;
import com.xml.read.ParseLines;
/**
 * This class looks at the spread and loads the game line table with this weeks odds.
 * It should be the first step in setting up this weeks data.
 * 
 * @author pegasus
 *
 */
public class UpdateGameLineTable {

	static Logger log = Logger.getLogger(UpdateGameLineTable.class);
	
	public static void main(String[] args) {
		ParseLines pl = null;
		Schedule schedule = null;
        GameLineBO glBO = null;
        try {
        	pl =  new ParseLines();
        	
			schedule = pl.parseXMLToSchedule("http://sportsfeeds.bovada.lv/basic/NCF.xml");
			if(schedule != null){
				glBO = new GameLineBO();
				glBO.loadLines(schedule);
			}
		} catch (Exception e) {
			log.error(e);
			log.info("UpdateGameLineTable has finished with an error...");
			System.exit(-99);
		}
		log.info("UpdateGameLineTable has finished...");
		System.exit(0);
	}

}
