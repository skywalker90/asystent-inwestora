package com.agsupport.core.jpa.facade;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import com.agsupport.core.jpa.model.StockIndex;

/**
 * Fasada zarządzająca obiektami typu StockIndex znajdującymi się w relacyjnej
 * bazie danych.
 * 
 * @author Michał Gruszczyński
 * 
 */

@Stateless
public class StockIndexFacade {

	private Logger logger = Logger.getLogger(StockIndexFacade.class);

	@PersistenceContext(unitName = "jpaAGS")
	private EntityManager em;

	/**
	 * Sprawdzenie czy dany indeks nie jest już zapisany dla danej giełdy.
	 * 
	 * @param time
	 *            czas zapisany w formacie hh:mm, pobrany z pierwszej kolumny
	 * @param d
	 *            data aktualizacji strony
	 * @param stockMarketId
	 *            id giełdy
	 * @return
	 */
	public boolean stockIndexExists(String time, Date d, long stockMarketId) {
		try {
			TypedQuery<StockIndex> query = em
					.createQuery(
							"SELECT si FROM StockIndex si WHERE si.timeOfLastUpdate=:time AND si.dateOfPageUpdate = :dateOfPageUpdate AND si.stockMarket.id = :stockMarketId",
							StockIndex.class);
			query.setParameter("time", time)
					.setParameter("stockMarketId", stockMarketId)
					.setParameter("dateOfPageUpdate", d, TemporalType.TIMESTAMP);
			if (query.getResultList().isEmpty()) {
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("StockIndexFacade.stockIndexExists", e);
			return false;
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
