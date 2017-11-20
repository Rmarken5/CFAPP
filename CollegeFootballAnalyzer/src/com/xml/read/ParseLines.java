package com.xml.read;

import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;

import com.entities.Choice;
import com.entities.Competitor;
import com.entities.Date;
import com.entities.Event;
import com.entities.EventType;
import com.entities.Line;
import com.entities.Odds;
import com.entities.Schedule;
import com.xml.validation.SourceValidator;



public class ParseLines {
	Logger log = Logger.getLogger(ParseLines.class);
    SourceValidator sourceValidator = new SourceValidator();
	DateFormat timeFormat = new SimpleDateFormat("hh:mm");
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	DateFormat altDateFormat = new SimpleDateFormat("EEE, MMM dd, yy");
	
	public Schedule parseXMLToSchedule(String url) throws Exception{
		Schedule schedule = null;
		SAXReader reader = null;
		URL link = null;
		Document document = null;
		Element scheduleElement = null;
		
		try{
			if(sourceValidator.isUrlXMLFormat(url)){
				reader = new SAXReader();
				link = new URL(url);
				document = reader.read(link.openStream());
				log.info(document.getRootElement());
				scheduleElement = document.getRootElement();
				schedule = parseSchedule(scheduleElement);
			}
			
		}catch(Exception e){
			throw e;
		}
		return schedule;
		
	}
	private Schedule parseSchedule(Element rootElement) throws ParseException{
		Element element = null;
		DefaultAttribute attribute = null;
		List<DefaultAttribute> attibutes = null;
		Iterator<DefaultAttribute> atrIter = null;
		String atrValue = null;
		Schedule schedule = null;
		EventType eventType = null;
		if(rootElement != null && rootElement.getName().equals("Schedule")){
			schedule = new Schedule();
			attibutes = rootElement.attributes();
			atrIter = attibutes.iterator();
			while(atrIter.hasNext()){
				attribute = atrIter.next();
				if(attribute.getName().equalsIgnoreCase("PUBLISH_TIME")){
					atrValue = attribute.getStringValue();
					schedule.setPublishTime(new Time(timeFormat.parse(attribute.getText()).getTime()));
					
				}else if(attribute.getName().equalsIgnoreCase("TS")){
					schedule.setMilliseconds(Long.parseLong(attribute.getText()));
				}else if(attribute.getName().equalsIgnoreCase("PUBLISH_DATE")){
					schedule.setPublishedDate(dateFormat.parse(attribute.getText()));
				}
			}
			if(rootElement.content() != null){
				element = (Element) rootElement.content().get(0);
				if(element.getName().equals("EventType")){
					eventType = parseEventType(element);
					if(eventType != null){
						schedule.setEventType(eventType);
					}
				}
			}
		}
		return schedule;
	}
	private EventType parseEventType(Element eventType) throws ParseException{
		EventType etObject = null;
		List<DefaultAttribute> attributes = null;
		Iterator<DefaultAttribute> atrIter = null;
		DefaultAttribute attribute = null;
		Element element = null;
		Date lineDate = null;
		if(eventType != null && eventType.getName().equals("EventType")){
			log.info(eventType.toString());
			attributes = eventType.attributes();
			if(attributes != null && attributes.size() > 0){
				etObject = new EventType();
				atrIter = attributes.iterator();
				while(atrIter.hasNext()){
					attribute = atrIter.next();
					String attrbuteName = attribute.getName();
					if(attrbuteName.equalsIgnoreCase("GROUP")){
						etObject.setGroup(attribute.getText());
					}else if (attrbuteName.equalsIgnoreCase("ID")){
						etObject.setId(attribute.getText());
					}else if (attrbuteName.equalsIgnoreCase("NAME")){
						etObject.setName(attribute.getText());
					}else if(attrbuteName.equalsIgnoreCase("SPORT")){
						etObject.setSport(attribute.getText());
					}
					
				}
				if(eventType.content() != null && eventType.content().size() > 0){
					for(Iterator<Element> eIt = (Iterator<Element>)eventType.content().iterator(); eIt.hasNext();){
						Element eO = eIt.next();
						String elementName = eO.getName();
						if(elementName.equalsIgnoreCase("DATE")){
							etObject.addDate(this.parseElementToDate(eO));
						}else if(elementName.equalsIgnoreCase("SEGMENT")){
							
						}
							
						log.info(eO.getName());
					}
				}
			}
		}
		return etObject;
	}
	private Date parseElementToDate(Element element) throws ParseException {
        Date result = null;
		Iterator<DefaultAttribute> atrIter = null;
		DefaultAttribute attribute = null;
		Element eventElement = null;
		if(element != null && element.attributes() != null && element.attributes().size() > 0 ){
			atrIter = element.attributes().iterator();
			result = new Date();

			while(atrIter.hasNext()){
				attribute = atrIter.next();
				String atrName = attribute.getName();
				if(atrName.equalsIgnoreCase("DTEXT")){
					result.setDateText(attribute.getText());
					result.setDateObject(altDateFormat.parse(attribute.getText()));
				}else if(atrName.equalsIgnoreCase("TS")){
					result.setTimeMilliseconds(Long.parseLong(attribute.getText()));
				}
			}
			if(element.content() != null && element.content().size() > 0 ){
				for(Iterator<Element> elmIter = (Iterator<Element>)element.content().iterator(); elmIter.hasNext();){
				    Element elm = elmIter.next();
				    if(elm.getName().equalsIgnoreCase("EVENT")){
					    result.addEvent(parseElementToEvent(elm));
				    }
				}
			}
		}
		return result;
	}
	
    private Event parseElementToEvent(Element element){
    	Event result = null;
    	Iterator<DefaultAttribute> atrIter = null;
    	String attributeName = null;
    	DefaultAttribute attribute = null;
    	String attributeText = null;
    	Element competitorElement = null;
    	Element timeElement = null;
    	if(element != null && element.attributes() != null && element.attributes().size() > 0 ){
    		atrIter = element.attributes().iterator();
    		result = new Event();
    		while(atrIter.hasNext()){
    			attribute = atrIter.next();
    			attributeName = attribute.getName();
    			attributeText = attribute.getText();
    			if(attributeName.equalsIgnoreCase("DenySameGame")){
    				result.setDenySameGame(Boolean.parseBoolean(attributeText));
    			}else if (attributeName.equalsIgnoreCase("GAME_STATUS")){
    				result.setGameStatus(attributeText);
    			}else if (attributeName.equalsIgnoreCase("ID")){
    				result.setId(attributeText);
    			}else if (attributeName.equalsIgnoreCase("LIVE_ENABLED")){
    				result.setLiveEnabled(Boolean.parseBoolean(attributeText));
    			}else if (attributeName.equalsIgnoreCase("LineOrder")){
    				result.setLineOrder(attributeText);
    			}else if (attributeName.equalsIgnoreCase("NAME")){
    				result.setName(attributeText);
    			}else if (attributeName.equalsIgnoreCase("COMPETITION")){
    				result.setCompetition(attributeText);
    			}else if (attributeName.equalsIgnoreCase("PXP_GAME_ID")){
    				result.setPlayByPlayGameId(attributeText);
    			}else if (attributeName.equalsIgnoreCase("Publish")){
    				result.setPublish(Boolean.parseBoolean(attributeText));
    			}else if (attributeName.equalsIgnoreCase("STATUS")){
    				result.setStatus(attributeText);
    			}else if (attributeName.equalsIgnoreCase("GROUP")){
                    result.setGroup(attributeText);   				
    			}
    		}
    		if(element.content() != null && element.content().size() > 0){
    			competitorElement = ( Element ) element.content().get(0);
    			if(competitorElement.getName().equalsIgnoreCase("COMPETITOR")){
                  
    				result.setCompetitorOne(parseElementToCompetitor(competitorElement) );
    			}
                competitorElement = ( Element ) element.content().get(1);
    			if(competitorElement.getName().equalsIgnoreCase("COMPETITOR")){
        			
                    result.setCompetitorTwo(parseElementToCompetitor(competitorElement));
    			}

                if(element.content().size() > 2){
                	timeElement = ( Element ) element.content().get(2);
                	if(timeElement.getName().equalsIgnoreCase("TIME")){
                	    result.setTime(parseElementToTime(timeElement));
                	}
                }
    		}
    	}
    	return result;
    }
    private Competitor parseElementToCompetitor(Element element){
    	Competitor result = null;
    	Iterator<DefaultAttribute> atrIter = null;
    	String attributeName = null;
    	DefaultAttribute attribute = null;
    	String attributeText = null;
    	if(element != null && element.attributes() != null && element.attributes().size() > 0){
    		atrIter = element.attributeIterator();
    		result = new Competitor();
    		while(atrIter.hasNext()){
    			attribute = atrIter.next();
    			attributeText = attribute.getText();
    			attributeName = attribute.getName();
    			if("ID".equalsIgnoreCase(attributeName)){
    				result.setId(attribute.getText());
    			}else if("NAME".equalsIgnoreCase(attributeName)){
    				result.setName(attributeText);
    			}else if("NUM".equalsIgnoreCase(attributeName)){
    				result.setNum(attributeText);
    			}else if("ROT".equalsIgnoreCase(attributeName)){
    				result.setRot(attributeText);
    			}
    		}
    		if(element.content() != null && element.content().size() > 0 ){
    			result.setLine(parseElementToLine((Element)element.content().get(0)));
    		}
    	}
    	return result;
    }
    private Line parseElementToLine(Element element){
    	Line result = null;
    	Iterator<DefaultAttribute> atrIter = null;
    	String attributeName = null;
    	DefaultAttribute attribute = null;
    	String attributeText = null;
    	if(element != null && element.attributes() != null && element.attributes().size() > 0){
    		atrIter = element.attributeIterator();
    		result = new Line();
    		while(atrIter.hasNext()){
    			
    			attribute = atrIter.next();	
    			attributeName = attribute.getName();
    			attributeText = attribute.getText();
    			if("ORDER".equalsIgnoreCase(attributeName)){
    				result.setOrder(attributeText);
    			}else if("TYPE".equalsIgnoreCase(attributeName)){
    				result.setType(attributeText);
    			}
    		}
    		if(element.hasContent()){
    			result.setChoice(parseElementToChoice((Element)element.content().get(0)));
    		}
    		
    	}
    	return result;
    }
    private com.entities.Time parseElementToTime(Element element){
    	com.entities.Time time = null;
    	Iterator<DefaultAttribute> atrIter = null;
    	String attributeName = null;
    	DefaultAttribute attribute = null;
    	String attributeText = null;
    	if(element != null && element.attributes() != null && element.attributes().size() > 0){
    		atrIter = element.attributeIterator();
    		time = new com.entities.Time();
    		while(atrIter.hasNext()){
    			attribute = atrIter.next();
    			attributeText = attribute.getText();
    			attributeName = attribute.getName();
    			
    			if("TTEXT".equalsIgnoreCase(attributeName)){
    				time.settText(attributeText);
    			}else if("TS".equalsIgnoreCase(attributeName)){
    				time.setTs(attributeText);
    			}
    		}
    	}
    	return time;
    }
    private Choice parseElementToChoice(Element element){
    	Choice choice = null;
    	Iterator<DefaultAttribute> atrIter = null;
    	String attributeName = null;
    	DefaultAttribute attribute = null;
    	String attributeText = null;
    	
    	if(element != null && element.attributes() != null && element.attributes().size() > 0 ){
    		atrIter = element.attributeIterator();
    		
    		choice = new Choice();
    		
    		while(atrIter.hasNext()){
    			attribute = atrIter.next();
    			attributeName = attribute.getName();
    			attributeText = attribute.getText();
    			if("ID".equalsIgnoreCase(attribute.getName())){
    				choice.setId(attributeText);
    			}else if("NUMBER".equalsIgnoreCase(attribute.getName())){
    				choice.setNumber(attributeText);
    			}else if("TS".equalsIgnoreCase(attribute.getName())){
    				choice.setTimeInMillisec(Long.parseLong(attributeText));;
    			}else if("VALUE".equalsIgnoreCase(attribute.getName())){
    				choice.setValue(attributeText);
    			}
    		}
    		if(element.content() != null && element.content().size() > 0){
    			choice.setOdds(this.parsedElementToOdds((Element)element.content().get(0)));
    		}
    	}
    	return choice;
    }
    
    private Odds parsedElementToOdds(Element element){
    	Odds odds = null;
    	Iterator<DefaultAttribute> atrIter = null;
    	String attributeName = null;
    	DefaultAttribute attribute = null;
    	String attributeText = null;
    	
    	if(element != null && element.attributes() != null && element.attributeCount() > 0){
    		atrIter = element.attributeIterator();
    		odds = new Odds();
    		while(atrIter.hasNext()){
    			attribute = atrIter.next();
    			attributeName = attribute.getName();
    			attributeText = attribute.getText();
    			
    			if("FRACTION".equalsIgnoreCase(attributeName)){
    				odds.setFraction(attributeText);
    			}else if("LINE".equalsIgnoreCase(attributeName)){
    				odds.setLine(attributeText);
    			}else if("MULTIPLIER".equalsIgnoreCase(attributeName)){
    				odds.setMultiplier(attributeText);
    			}else if("RISK".equalsIgnoreCase(attributeName)){
    				odds.setRisk(attributeText);
    			}else if("WIN".equalsIgnoreCase(attributeName)){
    				odds.setWin(attributeText);
    			}
    			
    		}
    	}
    	
    	return odds;
    }
}
