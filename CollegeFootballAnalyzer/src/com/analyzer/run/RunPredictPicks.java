package com.analyzer.run;

import com.analyzer.bo.PickBO;
/**
 * This class loads the pick table with the systems predictions. This is the second step to run
 * after the game line table has been populated.
 * @author pegasus
 *
 */
public class RunPredictPicks {

	
	public static void main(String[] args) {
		PickBO pickBO = new PickBO();
		try{
	    	pickBO.predictLines();
		System.out.println("Execution Complete");
		}catch(Exception e){
			e.printStackTrace();
		}
		System.exit(0);
	}
}
