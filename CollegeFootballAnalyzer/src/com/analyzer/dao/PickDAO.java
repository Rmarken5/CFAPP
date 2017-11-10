package com.analyzer.dao;

import java.util.List;

import com.entities.Pick;

public interface PickDAO {
	
	public void insertPickIntoTable(Pick pick);
	
	public void updatePick(Pick pick);
	
	public List<Pick> getPicksByWeek(Long weekNum);
	
	public Long getMaxWeekNum();
	
}
