package com.entities;

public class Odds {
	private String fraction;
	private String line;
	private String multiplier;
	private String risk;
	private String win;
	
	
	public String getFraction() {
		return fraction;
	}
	public void setFraction(String fraction) {
		this.fraction = fraction;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(String multiplier) {
		this.multiplier = multiplier;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}
	@Override
	public String toString() {
		return "Odds [fraction=" + fraction + ", line=" + line + ", multiplier=" + multiplier + ", risk=" + risk
				+ ", win=" + win + "]";
	}
	
}
