package com.agsupport.core.jpa.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import com.agsupport.core.jpa.model.Company;
import com.agsupport.core.jpa.model.StockIndex;
import com.agsupport.core.jpa.model.StockMarket;

/**
 * Fasada zarządzająca obiektami typu StockMarket znajdującymi się w relacyjnej
 * bazie danych.
 * 
 * @author Michał Gruszczyński
 * 
 */

@Stateless
public class StockMartekFacade {

	private Logger logger = Logger.getLogger(StockMartekFacade.class);

	@PersistenceContext(unitName = "jpaAGS")
	private EntityManager em;

	/**
	 * Stworzenie w bazie danych giełdy
	 * 
	 * @param stockMarket
	 *            tworzona giełda
	 * @return
	 */
	public boolean createStockMarket(StockMarket stockMarket) {
		try {
			stockMarket.setDateOfAdd(new Date());
			em.persist(stockMarket);
		} catch (Exception e) {
			logger.error("createStockMarket.Excpetion", e);
			return false;
		}
		return true;
	}

	public List<StockMarket> getStockMarketList() {
		try {
			TypedQuery<StockMarket> query = em.createQuery("from StockMarket",
					StockMarket.class);
			return query.getResultList();
		} catch (Exception e) {
			logger.error("getStockMarketList.Excpetion", e);
			return null;
		}

	}

	/**
	 * Pobieranie giełdy na podstawie id
	 * 
	 * @param stockMarketId
	 *            id giełdy
	 * @return
	 */
	public StockMarket getStockMarketById(long stockMarketId) {
		try {
			StockMarket stockMarket = em.find(StockMarket.class, stockMarketId);
			return stockMarket;
		} catch (Exception e) {
			logger.error("getStockMarketById.Excpetion", e);
			return null;
		}
	}

	/**
	 * Pobieranie giełdy na podstawie skróconej nazwy
	 * 
	 * @param abberviatedName
	 *            skrócona nazwa
	 * @return
	 */
	public StockMarket getStockMarketByAbbreviatedName(String abberviatedName) {
		try {
			TypedQuery<StockMarket> query = em.createQuery(
					"from StockMarket s where s.abbreviatedName =:name",
					StockMarket.class);
			query.setParameter("name", abberviatedName);
			StockMarket stockMarket = query.getSingleResult();
			return stockMarket;
		} catch (Exception e) {
			logger.error("getStockMarketByAbbreviatedName.Excpetion", e);
			return null;
		}
	}

	/**
	 * Akutalizacja giełdy zznajdującej się w bazie
	 * 
	 * @param stockMarket
	 *            giełda znajdująca się w bazie (pole id musi być zgodne, z
	 *            obiektem aktualizowanym w bazie)
	 * @return
	 */
	public boolean updateStockMarket(StockMarket stockMarket) {
		try {
			em.merge(stockMarket);
		} catch (Exception e) {
			logger.error("updateStockMarket.Excpetion", e);
			return false;
		}
		return true;
	}

	/**
	 * Usunięcie giełdy z bazy danych
	 * 
	 * @param stockMarketId
	 *            id usuwanej giełdy
	 * @return
	 */
	public boolean deleteStockMarket(long stockMarketId) {
		try {
			StockMarket stockMarket = em.find(StockMarket.class, stockMarketId);
			em.remove(stockMarket);
		} catch (Exception e) {
			logger.error("deleteStockMarket.Excpetion", e);
			return false;
		}
		return true;
	}

	/**
	 * Dodanie spółki do danej giełdy
	 * 
	 * @param stockMarketId
	 *            id giełdy do której dodawana jest spółka
	 * @param company
	 *            spółka
	 * @return
	 */
	public boolean addCompany(long stockMarketId, Company company) {
		try {
			StockMarket stockMarket = em.find(StockMarket.class, stockMarketId);
			company.setStockMarket(stockMarket);
			company.setDateOfAdd(new Date());
			em.persist(company);
		} catch (Exception e) {
			logger.error("addCompany.Excpetion", e);
			return false;
		}
		return true;
	}

	/**
	 * Usunięcie danej spółki
	 * 
	 * @param comapnyId
	 *            id usuwanej spółki
	 * @return
	 */
	public boolean deleteCompany(long comapnyId) {
		try {
			Company company = em.find(Company.class, comapnyId);
			StockMarket stockMarket = company.getStockMarket();
			stockMarket.getCompanies().remove(company);
			em.remove(company);
		} catch (Exception e) {
			logger.error("deleteCompany.Excpetion", e);
			return false;
		}
		return true;
	}

	/**
	 * Dodanie wartości indeksu giełdowego do danej giełdy
	 * 
	 * @param stockMarketId
	 *            id giełdy
	 * @param stockIndex
	 *            indeks giełdowy
	 * @return
	 */
	public boolean addStockIndex(long stockMarketId, StockIndex stockIndex) {
		try {
			StockMarket stockMarket = em.find(StockMarket.class, stockMarketId);
			stockIndex.setStockMarket(stockMarket);
			stockIndex.setDateOfAdd(new Date());
			em.persist(stockIndex);
		} catch (Exception e) {
			logger.error("addStockIndex.Excpetion", e);
			return false;
		}
		return true;
	}

	/**
	 * Usunięcie danej wartości indeksu giełdowego
	 * 
	 * @param stockIndexId
	 *            id indeksu giełdowego
	 * @return
	 */
	public boolean deleteStockIndex(long stockIndexId) {
		try {
			StockIndex stockIndex = em.find(StockIndex.class, stockIndexId);
			StockMarket stockMarket = stockIndex.getStockMarket();
			stockMarket.getStockIndexes().remove(stockIndex);
			em.remove(stockIndex);
		} catch (Exception e) {
			logger.error("deleteStockIndex.Excpetion", e);
			return false;
		}
		return true;
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
