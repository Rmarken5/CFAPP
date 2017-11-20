package com.analyzer.run;

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
			e.printStackTrace();
		}
		
	}

}
