package com.analyzer.run;

import com.analyzer.bo.PickBO;

public class UpdatePickTable {

	
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
