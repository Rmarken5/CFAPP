package com.entities;

import java.io.Serializable;

public class GameLine implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8009541780426216950L;
	
	private Long id;
	private Long weekNumber;
	private Team homeTeam;
	private Team awayTeam;
	private Double spread;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(Long weekNumber) {
		this.weekNumber = weekNumber;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	public Double getSpread() {
		return spread;
	}
	public void setSpread(Double spread) {
		this.spread = spread;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		result = prime * result + ((spread == null) ? 0 : spread.hashCode());
		result = prime * result + ((weekNumber == null) ? 0 : weekNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameLine other = (GameLine) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		if (spread == null) {
			if (other.spread != null)
				return false;
		} else if (!spread.equals(other.spread))
			return false;
		if (weekNumber == null) {
			if (other.weekNumber != null)
				return false;
		} else if (!weekNumber.equals(other.weekNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GameLine [id=" + id + ", weekNumber=" + weekNumber + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam
				+ ", spread=" + spread + "]";
	}
	
	
}
