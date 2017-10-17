package com.entities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Date {

	private String dateText;
	private java.util.Date dateObject;
	private Long timeMilliseconds;
	private Timestamp msAsTimestamp;
	private List<Event> events;
	
	public void addEvent(Event event){
		if(this.events == null ){
			events = new ArrayList<Event>();
		}
		events.add(event);
	}
	public String getDateText() {
		return dateText;
	}
	public void setDateText(String dateText) {
		this.dateText = dateText;
	}
	public java.util.Date getDateObject() throws ParseException {
		if(this.dateText != null && !"".equals(dateText)){
			DateFormat df = new SimpleDateFormat("ddd, MMM d, yy");
		    setDateObject(df.parse(this.getDateText()));
		}
		
		return dateObject;
	}
	public void setDateObject(java.util.Date dateObject) {
		this.dateObject = dateObject;
	}
	public Long getTimeMilliseconds() {
		return timeMilliseconds;
	}
	public void setTimeMilliseconds(Long timeMilliseconds) {
		this.timeMilliseconds = timeMilliseconds;
	}
	public Timestamp getMsAsTimestamp() {
		if(this.timeMilliseconds != null){
			setMsAsTimestamp(new Timestamp(getTimeMilliseconds()));
		}
		return msAsTimestamp;
	}
	public void setMsAsTimestamp(Timestamp msAsTimestamp) {
		this.msAsTimestamp = msAsTimestamp;
	}

	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	@Override
	public String toString() {
		return "Date [dateText=" + dateText + ", dateObject=" + dateObject + ", timeMilliseconds=" + timeMilliseconds
				+ ", msAsTimestamp=" + msAsTimestamp + ", events=" + events + "]";
	}
	
	
}
