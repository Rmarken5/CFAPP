package com.entities;

import java.sql.Timestamp;

public class Game {
	private Long id;
	private int weekNumber;
	private Timestamp dateGame;
	private Opponent winner;
	private Opponent loser;
	private String notes;
	private String broadcaster;
	private Opponent homeTeam;
	private Opponent awayTeam;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}
	public Timestamp getDateGame() {
		return dateGame;
	}
	public void setDateGame(Timestamp dateGame) {
		this.dateGame = dateGame;
	}
	public Opponent getWinner() {
		return winner;
	}
	public void setWinner(Opponent winner) {
		this.winner = winner;
	}
	public Opponent getLoser() {
		return loser;
	}
	public void setLoser(Opponent loser) {
		this.loser = loser;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getBroadcaster() {
		return broadcaster;
	}
	public void setBroadcaster(String broadcaster) {
		this.broadcaster = broadcaster;
	}
	public Opponent getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Opponent homeTeam) {
		this.homeTeam = homeTeam;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((broadcaster == null) ? 0 : broadcaster.hashCode());
		result = prime * result + ((dateGame == null) ? 0 : dateGame.hashCode());
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		result = prime * result + ((loser == null) ? 0 : loser.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + weekNumber;
		result = prime * result + ((winner == null) ? 0 : winner.hashCode());
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
		Game other = (Game) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (broadcaster == null) {
			if (other.broadcaster != null)
				return false;
		} else if (!broadcaster.equals(other.broadcaster))
			return false;
		if (dateGame == null) {
			if (other.dateGame != null)
				return false;
		} else if (!dateGame.equals(other.dateGame))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		if (loser == null) {
			if (other.loser != null)
				return false;
		} else if (!loser.equals(other.loser))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (weekNumber != other.weekNumber)
			return false;
		if (winner == null) {
			if (other.winner != null)
				return false;
		} else if (!winner.equals(other.winner))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Game [id=" + id + ", weekNumber=" + weekNumber + ", dateGame=" + dateGame + ", winner=" + winner
				+ ", loser=" + loser + ", notes=" + notes + ", broadcaster=" + broadcaster + ", homeTeam=" + homeTeam
				+ ", awayTeam=" + awayTeam + "]";
	}
	public Opponent getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Opponent awayTeam) {
		this.awayTeam = awayTeam;
	}
	
	
	
}
