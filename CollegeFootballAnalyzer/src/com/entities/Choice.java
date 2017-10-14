package com.entities;

public class Choice {
	private String id;
	private String number;
	private Long timeInMillisec;
	private String value;
	private Odds odds;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Long getTimeInMillisec() {
		return timeInMillisec;
	}
	public void setTimeInMillisec(Long timeInMillisec) {
		this.timeInMillisec = timeInMillisec;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Odds getOdds() {
		return odds;
	}
	public void setOdds(Odds odds) {
		this.odds = odds;
	}
	@Override
	public String toString() {
		return "Choice [id=" + id + ", number=" + number + ", timeInMillisec=" + timeInMillisec + ", value=" + value
				+ ", odds=" + odds + "]";
	}
	
}
