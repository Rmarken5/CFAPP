package com.entities;

import java.util.ArrayList;
import java.util.List;

public class EventType {

	private String group;
	private String id;
	private String name;
	private String sport;
	List<com.entities.Date> dates;
	
	
    public void addDate(com.entities.Date date){
    	if(this.dates == null ){
    		this.dates = new ArrayList<com.entities.Date>();
    	}
    	dates.add(date);
    }
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public List<com.entities.Date> getDates() {
		return dates;
	}
	public void setDates(List<com.entities.Date> dates) {
		this.dates = dates;
	}
	@Override
	public String toString() {
		return "EventType [group=" + group + ", id=" + id + ", name=" + name + ", sport=" + sport + ", dates=" + dates
				+ "]";
	}
	
	
}
