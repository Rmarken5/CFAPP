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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateGame == null) ? 0 : dateGame.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (dateGame == null) {
			if (other.dateGame != null)
				return false;
		} else if (!dateGame.equals(other.dateGame))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
				+ ", loser=" + loser + ", notes=" + notes + ", broadcaster=" + broadcaster + "]";
	}
	
	
	
}
