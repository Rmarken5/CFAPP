package test;

import com.analyzer.bo.PickBO;
import com.email.communication.Mailer;

public class Test {

	public static void main(String[] args) {
		PickBO pickBO = new PickBO();
		Long weekNumber = 12L;
		
		String results;
		try {
			results = pickBO.pickListForWeekNumber(weekNumber);
			System.out.println(results);
			Mailer.send("send.markenapps@gmail.com","Zeppelin32!","marken.ryan@gmail.com","Week " + weekNumber + " Lines.",results); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);
		
		
	}
}
