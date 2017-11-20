package test;

import org.apache.log4j.Logger;

import com.analyzer.bo.PickBO;
import com.email.communication.Mailer;

public class Test {

	public static void main(String[] args) {
		
		Logger log = Logger.getLogger(Test.class);
		
		log.error("This is an error.");
		log.debug("This is debug");
		log.info("This is info");
		log.warn("This is warn");
        log.fatal("This is fatal");		
		System.exit(0);
		
		
	}
}
