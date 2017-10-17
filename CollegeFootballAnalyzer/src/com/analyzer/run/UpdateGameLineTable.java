package com.analyzer.run;

import com.analyzer.bo.GameLineBO;
import com.entities.Schedule;
import com.xml.read.ParseLines;

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
