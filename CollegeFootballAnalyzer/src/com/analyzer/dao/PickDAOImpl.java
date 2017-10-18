package com.analyzer.dao;

import org.hibernate.Session;

import com.analyzer.service.SessionServiceImpl;
import com.entities.Pick;

public class PickDAOImpl implements PickDAO {

	@Override
	public void insertPickIntoTable(Pick pick) {
		Session session = null;
		try {
			if (pick != null) {
				session = SessionServiceImpl.getSession();
				session.beginTransaction();
				session.persist(pick);
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void updatePick(Pick pick) {
		Session session = null;
		try{
			if(pick != null && pick.getPickId() != null){
				session = SessionServiceImpl.getSession();
				session.beginTransaction();
				session.merge(pick);
				session.getTransaction().commit();
			}
		}catch(Exception e){
			throw e;
		}
	}
	private void setUpdatePickFields(Pick existingPick, Pick newPick){
		if(existingPick != null && newPick != null){
			existingPick.setAwayTeam(newPick.getAwayTeam());
			existingPick.setHomeTeam(newPick.getHomeTeam());
			existingPick.setFavoriteTeam(newPick.getFavoriteTeam());
			existingPick.setIsPickedCorrectly(newPick.getIsPickedCorrectly());
			existingPick.setSpread(newPick.getSpread());
			existingPick.setWeekNumber(newPick.getWeekNumber());
		}
	}
}
