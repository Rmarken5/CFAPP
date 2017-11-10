package com.analyzer.service;

import java.util.List;

import com.entities.Pick;

public interface PickService {

	public void insertPickIntoTable(Pick pick);
	
	public void updatePick(Pick pick);
	
	public List<Pick> getPicksByWeek(Long weekNum);
	
	public Long getMaxWeekNum();
}
