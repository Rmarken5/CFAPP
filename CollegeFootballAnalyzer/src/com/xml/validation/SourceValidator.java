package com.xml.validation;

import org.apache.commons.validator.routines.UrlValidator;


public class SourceValidator {

	public boolean isUrlXMLFormat(String url){
		try {
			if(isUrlValid(url)){
				return true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	
	private boolean isUrlValid(String url) throws Exception {
		UrlValidator urlValidator = null;
		try{
			if(url != null && !url.trim().equals("")){
			  urlValidator = new UrlValidator(new String[]{"http", "https"});
			  return urlValidator.isValid(url);
			}else{
			  return false;
			}
		}catch(Exception e){
			
			System.out.println(e.getStackTrace());
			return false;
		}
	}
}
