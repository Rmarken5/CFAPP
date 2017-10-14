package com.entities;

import java.sql.Time;
import java.sql.Timestamp;

public class Schedule  {
	private java.util.Date publishedDate;
	private Time publishTime;
	private Long milliseconds;
	private Timestamp timestamp;
	private EventType eventType;
	
	
	public java.util.Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(java.util.Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Time getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Time publishTime) {
		this.publishTime = publishTime;
	}
	public Long getMilliseconds() {
		return milliseconds;
	}
	public void setMilliseconds(Long milliseconds) {
		this.milliseconds = milliseconds;
	}
	public Timestamp getTimestamp() {
		if(this.milliseconds != null){
			setTimestamp(new Timestamp(getMilliseconds()));
		}
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	@Override
	public String toString() {
		return "Schedule [publishedDate=" + publishedDate + ", publishTime=" + publishTime + ", milliseconds="
				+ milliseconds + ", timestamp=" + timestamp + ", eventType=" + eventType + "]";
	}
}
