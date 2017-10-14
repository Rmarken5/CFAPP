package com.entities;

public class Time {
	

	private String tText;
	private String ts;
	
	
	public String gettText() {
		return tText;
	}
	public void settText(String tText) {
		this.tText = tText;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	@Override
	public String toString() {
		return "Time [tText=" + tText + ", ts=" + ts + "]";
	}
}
