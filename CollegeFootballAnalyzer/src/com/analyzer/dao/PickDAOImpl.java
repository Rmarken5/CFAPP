package com.analyzer.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

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
	
	@Override
	public List<Pick> getPicksByWeek(Long weekNum) {
		Session session = null;
		final String GET_PICKS_FROM_WEEK = "SELECT p FROM Pick p where p.weekNumber = :weekNum";
		Query q = null;
		List<Pick> picks = null;
		try {

			if (weekNum != null) {
				session = SessionServiceImpl.getSession();
				
				session.getTransaction().begin();
				
				q = session.createQuery(GET_PICKS_FROM_WEEK, Pick.class);
				q.setParameter("weekNum", weekNum);

				picks = q.getResultList();
				
				session.getTransaction().commit();
			}

		} catch (Exception e) {
			throw e;
		}
		return picks;
	}
	
	@Override
	public Long getMaxWeekNum() {
		Session session = null;
		final String GET_MAX_WEEK_NUM = "SELECT MAX(p.weekNumber) FROM Pick p";
		Query q = null;
		Long result = null;
		try {
			session = SessionServiceImpl.getSession();
			q = session.createQuery(GET_MAX_WEEK_NUM, Long.class);
			session.beginTransaction();
			result = (Long) q.getSingleResult();
			if(result == null){
				result = 0L;
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			throw e;
		}

		return result;
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
