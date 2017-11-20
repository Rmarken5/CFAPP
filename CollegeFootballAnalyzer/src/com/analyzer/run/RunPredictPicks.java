package com.analyzer.run;

import org.apache.log4j.Logger;

import com.analyzer.bo.PickBO;
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
		try{
	    	pickBO.predictLines();
	    	log.info("Execution Complete");
		}catch(Exception e){
			e.printStackTrace();
		}
		System.exit(0);
	}
}
