package com.agsupport.core.service.collection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.agsupport.core.jpa.facade.StockMartekFacade;
import com.agsupport.core.jpa.model.StockMarket;

/**
 * Klasa odpowiedzialna za pobranie światowych giełd.
 * @author Michał Gruszczyński
 *
 */

@Singleton
@Startup
public class StockMarketCollector {

	private Logger logger = Logger.getLogger(StockMarketCollector.class);

	private List<StockMarket> stockMarkets = null;

	@EJB
	private StockMartekFacade stockMarketFacade;

	@PostConstruct
	public void init() {
		logger.info("StockMarketCollector.init START");
		stockMarkets = stockMarketFacade.getStockMarketList();
		Connection connection = Jsoup
				.connect("http://gielda.wp.pl/typ,indeksyZagraniczne,notowania.html");
		try {
			Document document = connection.post();
			Elements tableElements = document.select("table.tab");
			Elements allTr = tableElements.select("tr");
			for (Element tr : allTr) {
				Elements tds = tr.select("tr > td");
				if (tds.isEmpty() || tds.size() < 3) {
					continue;
				}
				Element nazwaE = tds.get(1);
				String nazwa = nazwaE.text();
				if (stockMarketExists(nazwa) == false) {
					logger.info("StockMarketCollector.init - create new StockMarket");
					StockMarket stockMarket = new StockMarket();
					stockMarket.setAbbreviatedName(nazwa);
					stockMarketFacade.createStockMarket(stockMarket);
				}else{
					logger.info("StockMarketCollector.init - NOT create new StockMarket");
				}
			}

		} catch (MalformedURLException malfURLExc) {
			// request URL is not a HTTP or HTTPS URL, or is otherwise malformed
			logger.error("StockMarketCollector.init malfURLExc", malfURLExc);
		} catch (HttpStatusException httpStatusExc) {
			// response is not OK and HTTP response errors are not ignored
			logger.error("StockMarketCollector.init httpStatusExc",
					httpStatusExc);
		} catch (UnsupportedMimeTypeException unsupportedMimeTypeExc) {
			// response mime type is not supported and those errors are not
			// ignored
			logger.error("StockMarketCollector.init unsupportedMimeTypeExc",
					unsupportedMimeTypeExc);
		} catch (SocketTimeoutException socketTimeoutExc) {
			// connection times out
			logger.error("StockMarketCollector.init socketTimeoutExc",
					socketTimeoutExc);
		} catch (IOException ioExcpetion) {
			logger.error("StockMarketCollector.init ioExcpetion", ioExcpetion);
		} catch (Exception exception) {
			logger.error("StockMarketCollector.init exception", exception);
		}

		logger.info("StockMarketCollector.init END");
	}

	private boolean stockMarketExists(String stockMarketAbberviatedName) {
		for (StockMarket stockMarket : stockMarkets) {
			if (stockMarket.getAbbreviatedName().equals(
					stockMarketAbberviatedName)) {
				return true;
			}
		}
		return false;
	}

	public List<StockMarket> getStockMarkets() {
		return stockMarkets;
	}

	public void setStockMarkets(List<StockMarket> stockMarkets) {
		this.stockMarkets = stockMarkets;
	}

	public StockMartekFacade getStockMarketFacade() {
		return stockMarketFacade;
	}

	public void setStockMarketFacade(StockMartekFacade stockMarketFacade) {
		this.stockMarketFacade = stockMarketFacade;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
