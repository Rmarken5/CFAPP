package com.entities;

import java.io.Serializable;

public class Pick implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4754598614640544063L;

	private Long pickId;
	private Long weekNumber;
	private Team favoriteTeam;
	private Team homeTeam;
	private Team awayTeam;
	private Double spread;
	private Boolean isPickedCorrectly;

	public Long getPickId() {
		return pickId;
	}

	public void setPickId(Long pickId) {
		this.pickId = pickId;
	}

	public Long getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(Long weekNumber) {
		this.weekNumber = weekNumber;
	}

	public Team getFavoriteTeam() {
		return favoriteTeam;
	}

	public void setFavoriteTeam(Team favoriteTeam) {
		this.favoriteTeam = favoriteTeam;
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

	public Boolean getIsPickedCorrectly() {
		return isPickedCorrectly;
	}

	public void setIsPickedCorrectly(Boolean isPickedCorrectly) {
		this.isPickedCorrectly = isPickedCorrectly;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((favoriteTeam == null) ? 0 : favoriteTeam.hashCode());
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		result = prime * result + ((isPickedCorrectly == null) ? 0 : isPickedCorrectly.hashCode());
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
		Pick other = (Pick) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (favoriteTeam == null) {
			if (other.favoriteTeam != null)
				return false;
		} else if (!favoriteTeam.equals(other.favoriteTeam))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		if (isPickedCorrectly == null) {
			if (other.isPickedCorrectly != null)
				return false;
		} else if (!isPickedCorrectly.equals(other.isPickedCorrectly))
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
		return "Pick [pickId=" + pickId + ", weekNumber=" + weekNumber + ", favoriteTeam=" + favoriteTeam
				+ ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", spread=" + spread + ", isPickedCorrectly="
				+ isPickedCorrectly + "]";
	}

}
