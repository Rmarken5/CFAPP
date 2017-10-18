package com.analyzer.dao;

import com.entities.Pick;

public interface PickDAO {
	
	public void insertPickIntoTable(Pick pick);
	
	public void updatePick(Pick pick);
	
}
