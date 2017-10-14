package com.entities;

public class Line {
	private String order;
	private String type;
	private Choice choice;
	
	
	public Choice getChoice() {
		return choice;
	}

	public void setChoice(Choice choice) {
		this.choice = choice;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Line [order=" + order + ", type=" + type + ", choice=" + choice + "]";
	}

}
