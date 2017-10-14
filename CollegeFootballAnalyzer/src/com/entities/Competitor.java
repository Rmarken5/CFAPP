package com.entities;

public class Competitor {

	private String id;
	private String name;
	private String num;
	private String rot;
	private Line line;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getRot() {
		return rot;
	}
	public void setRot(String rot) {
		this.rot = rot;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	@Override
	public String toString() {
		return "Competitor [id=" + id + ", name=" + name + ", num=" + num + ", rot=" + rot + ", line=" + line + "]";
	}
	
	
}
