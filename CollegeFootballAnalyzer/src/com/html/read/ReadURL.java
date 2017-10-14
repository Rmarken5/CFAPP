package com.html.read;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadURL {

	public String getHTMLFromURL(String URL) throws Exception{

		URL url;
		StringBuilder buffer = new StringBuilder();
		String temp = null;;
		try {
			url = new URL(URL);
			InputStream in = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			while (!(temp = reader.readLine().trim()).equals("</html>") ) {
				buffer.append(temp).append(System.lineSeparator());
			}
			

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		return buffer.toString();
	
	}
}
