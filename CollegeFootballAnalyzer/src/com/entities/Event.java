package com.entities;

public class Event {
	private Boolean denySameGame;
    private String gameStatus;
    private String Id;
    private Boolean liveEnabled;
    private String lineOrder;
    private String name;
    private String competition;
    private String playByPlayGameId;
    private Boolean publish;
    private String status;
    private String group;
    private Competitor competitorOne;
    private Competitor competitorTwo;
    private Time time;
    
	public Boolean getDenySameGame() {
		return denySameGame;
	}
	public void setDenySameGame(Boolean denySameGame) {
		this.denySameGame = denySameGame;
	}
	public String getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Boolean getLiveEnabled() {
		return liveEnabled;
	}
	public void setLiveEnabled(Boolean liveEnabled) {
		this.liveEnabled = liveEnabled;
	}
	public String getLineOrder() {
		return lineOrder;
	}
	public void setLineOrder(String lineOrder) {
		this.lineOrder = lineOrder;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompetition() {
		return competition;
	}
	public void setCompetition(String competition) {
		this.competition = competition;
	}
	public String getPlayByPlayGameId() {
		return playByPlayGameId;
	}
	public void setPlayByPlayGameId(String playByPlayGameId) {
		this.playByPlayGameId = playByPlayGameId;
	}
	public Boolean getPublish() {
		return publish;
	}
	public void setPublish(Boolean publish) {
		this.publish = publish;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Competitor getCompetitorOne() {
		return competitorOne;
	}
	public void setCompetitorOne(Competitor competitorOne) {
		this.competitorOne = competitorOne;
	}
	public Competitor getCompetitorTwo() {
		return competitorTwo;
	}
	public void setCompetitorTwo(Competitor competitorTwo) {
		this.competitorTwo = competitorTwo;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Event [denySameGame=" + denySameGame + ", gameStatus=" + gameStatus + ", Id=" + Id + ", liveEnabled="
				+ liveEnabled + ", lineOrder=" + lineOrder + ", name=" + name + ", competition=" + competition
				+ ", playByPlayGameId=" + playByPlayGameId + ", publish=" + publish + ", status=" + status + ", group="
				+ group + ", competitorOne=" + competitorOne + ", competitorTwo=" + competitorTwo + ", time=" + time
				+ "]";
	}
}
