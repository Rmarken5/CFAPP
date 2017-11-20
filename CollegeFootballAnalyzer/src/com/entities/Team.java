package com.entities;

import java.io.Serializable;

public class Team implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6334259399742625011L;
	
	private Long id;
	private String spreadTeamName;
	private String scheduleTeamName;
	private Long wins;
	private Long losses;
	private Long atsWins;
	private Long atsLosses;
	private Long winLossDifferenceSeason;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSpreadTeamName() {
		return spreadTeamName;
	}
	public void setSpreadTeamName(String spreadTeamName) {
		this.spreadTeamName = spreadTeamName;
	}
	public String getScheduleTeamName() {
		return scheduleTeamName;
	}
	public void setScheduleTeamName(String scheduleTeamName) {
		this.scheduleTeamName = scheduleTeamName;
	}
	public Long getWins() {
		return wins;
	}
	public void setWins(Long wins) {
		this.wins = wins;
	}
	public Long getLosses() {
		return losses;
	}
	public void setLosses(Long losses) {
		this.losses = losses;
	}
	public Long getAtsWins() {
		return atsWins;
	}
	public void setAtsWins(Long atsWins) {
		this.atsWins = atsWins;
	}
	public Long getAtsLosses() {
		return atsLosses;
	}
	public void setAtsLosses(Long atsLosses) {
		this.atsLosses = atsLosses;
	}
	public Long getWinLossDifferenceSeason() {
		return winLossDifferenceSeason;
	}
	public void setWinLossDifferenceSeason(Long winLossDifferenceSeason) {
		this.winLossDifferenceSeason = winLossDifferenceSeason;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atsLosses == null) ? 0 : atsLosses.hashCode());
		result = prime * result + ((atsWins == null) ? 0 : atsWins.hashCode());
		result = prime * result + ((losses == null) ? 0 : losses.hashCode());
		result = prime * result + ((scheduleTeamName == null) ? 0 : scheduleTeamName.hashCode());
		result = prime * result + ((spreadTeamName == null) ? 0 : spreadTeamName.hashCode());
		result = prime * result + ((winLossDifferenceSeason == null) ? 0 : winLossDifferenceSeason.hashCode());
		result = prime * result + ((wins == null) ? 0 : wins.hashCode());
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
		Team other = (Team) obj;
		if (atsLosses == null) {
			if (other.atsLosses != null)
				return false;
		} else if (!atsLosses.equals(other.atsLosses))
			return false;
		if (atsWins == null) {
			if (other.atsWins != null)
				return false;
		} else if (!atsWins.equals(other.atsWins))
			return false;
		if (losses == null) {
			if (other.losses != null)
				return false;
		} else if (!losses.equals(other.losses))
			return false;
		if (scheduleTeamName == null) {
			if (other.scheduleTeamName != null)
				return false;
		} else if (!scheduleTeamName.equals(other.scheduleTeamName))
			return false;
		if (spreadTeamName == null) {
			if (other.spreadTeamName != null)
				return false;
		} else if (!spreadTeamName.equals(other.spreadTeamName))
			return false;
		if (winLossDifferenceSeason == null) {
			if (other.winLossDifferenceSeason != null)
				return false;
		} else if (!winLossDifferenceSeason.equals(other.winLossDifferenceSeason))
			return false;
		if (wins == null) {
			if (other.wins != null)
				return false;
		} else if (!wins.equals(other.wins))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Team [id=" + id + ", spreadTeamName=" + spreadTeamName + ", scheduleTeamName=" + scheduleTeamName
				+ ", wins=" + wins + ", losses=" + losses + ", atsWins=" + atsWins + ", atsLosses=" + atsLosses
				+ ", winLossDifferenceSeason=" + winLossDifferenceSeason + "]";
	}
	
}
