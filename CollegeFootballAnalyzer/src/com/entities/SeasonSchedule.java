package com.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class SeasonSchedule implements Serializable{
     /** 
	 */
	private static final long serialVersionUID = -2746617490451886762L;
	
	private Long scheduleId;
	private Long weekNumber;
	private Timestamp gameTimestamp;
	private Team homeTeam;
	private Team awayTeam;
	private Long homeTeamScore;
	private Long awayTeamScore;
	private Team winningTeam;
	
	
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Long getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(Long weekNumber) {
		this.weekNumber = weekNumber;
	}
	public Timestamp getGameTimestamp() {
		return gameTimestamp;
	}
	public void setGameTimestamp(Timestamp gameTimestamp) {
		this.gameTimestamp = gameTimestamp;
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
	public Long getHomeTeamScore() {
		return homeTeamScore;
	}
	public void setHomeTeamScore(Long homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}
	public Long getAwayTeamScore() {
		return awayTeamScore;
	}
	public void setAwayTeamScore(Long awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}

	public Team getWinningTeam() {
		return winningTeam;
	}
	public void setWinningTeam(Team winningTeam) {
		this.winningTeam = winningTeam;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((awayTeamScore == null) ? 0 : awayTeamScore.hashCode());
		result = prime * result + ((gameTimestamp == null) ? 0 : gameTimestamp.hashCode());
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		result = prime * result + ((homeTeamScore == null) ? 0 : homeTeamScore.hashCode());
		result = prime * result + ((weekNumber == null) ? 0 : weekNumber.hashCode());
		result = prime * result + ((winningTeam == null) ? 0 : winningTeam.hashCode());
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
		SeasonSchedule other = (SeasonSchedule) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (awayTeamScore == null) {
			if (other.awayTeamScore != null)
				return false;
		} else if (!awayTeamScore.equals(other.awayTeamScore))
			return false;
		if (gameTimestamp == null) {
			if (other.gameTimestamp != null)
				return false;
		} else if (!gameTimestamp.equals(other.gameTimestamp))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		if (homeTeamScore == null) {
			if (other.homeTeamScore != null)
				return false;
		} else if (!homeTeamScore.equals(other.homeTeamScore))
			return false;
		if (weekNumber == null) {
			if (other.weekNumber != null)
				return false;
		} else if (!weekNumber.equals(other.weekNumber))
			return false;
		if (winningTeam == null) {
			if (other.winningTeam != null)
				return false;
		} else if (!winningTeam.equals(other.winningTeam))
			return false;
		return true;
	}
	
	
}
