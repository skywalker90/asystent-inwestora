package com.agsupport.core.service.collection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timeout;

import org.jboss.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.agsupport.core.jpa.facade.StockIndexFacade;
import com.agsupport.core.jpa.facade.StockMartekFacade;
import com.agsupport.core.jpa.model.StockIndex;
import com.agsupport.core.jpa.model.StockMarket;

/**
 * Klasa odpowiedzialna za systematyczne pobieranie indeksów dla światowych giełd.
 * @author Michał Gruszczyński
 *
 */

@Stateless
public class StockIndexCollector {

	private Logger logger = Logger.getLogger(StockIndexCollector.class);

	private static long a = 0;

	@EJB
	private StockMartekFacade stockMarketFacade;
	@EJB
	private StockIndexFacade stockindexFacade;

	private List<StockMarket> stockMarkets = null;

	@PostConstruct
	public void init() {
		logger.info("StockIndexCollector.init START");
	}

	@Schedule(persistent = false, second = "0", minute = "*/1", hour = "*")
	public void collect() {
		logger.info("StockIndexCollector.collect");

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd.MM.yyyy");
		Connection connection = Jsoup
				.connect("http://gielda.wp.pl/typ,indeksyZagraniczne,notowania.html");

		try {
			Document document = connection.post();

			// Wybranie daty
			Elements elements = document.select(".not_date2").select(".ml10")
					.select(".mr10").select(".fl");

			Date d = null;
			if (elements.size() == 1) {
				d = sdf.parse(elements.get(0).text());
			}

			Elements tableElements = document.select("table.tab");
			Elements allTr = tableElements.select("tr");

			parseRows(allTr, d);

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

	}

	private void parseRows(Elements allTr, Date d) {
		for (Element tr : allTr) {

			Elements tds = tr.select("tr > td");
			if (tds.isEmpty() || tds.size() < 3) {
				continue;
			}

			Element elTime = tds.get(0);
			Element elName = tds.get(1);
			Element elValue = tds.get(3);

			String time = elTime.text();
			String name = elName.text();
			String value = elValue.text();
			value = value.replaceAll("\\s", "");
			value = value.replaceAll(",", ".");
			value = value.replaceAll("\u00a0", "");
			value = value.replaceAll("\\s", "");

			if (value == null || value.isEmpty()) {
				logger.info("Value of index equals null");
				continue;
			}

			Double indexValue = null;
			try {
				indexValue = Double.parseDouble(value);
			} catch (NumberFormatException e) {
				logger.error("NumberFormatException parseDouble", e);
			}

			logger.info("time - " + time);
			logger.info("name - " + name);
			logger.info("value - " + value);
			logger.info("indexValue - " + indexValue);

			addStockIndex(time, name, indexValue, d);

		}
	}

	private boolean addStockIndex(String time, String name, Double value, Date d) {
		StockMarket stockMarket = stockMarketFacade
				.getStockMarketByAbbreviatedName(name);
		if (stockMarket == null) {
			logger.info("StockIndexCollector.addStockIndex - stockMarket = null");
			return false;
		}

		if (stockindexFacade.stockIndexExists(time, d, stockMarket.getId()) == true) {
			logger.info("StockIndexCollector.stockIndexExists = true");
			return false;
		}

		logger.info("StockIndexCollector.addStockIndex ");
		Date date = new Date();
		StockIndex stockIndex = new StockIndex();
		stockIndex.setDateOfAdd(date);
		stockIndex.setPrice(value);
		stockIndex.setDateOfPageUpdate(d);
		stockIndex.setTimeOfLastUpdate(time);
		if (stockMarketFacade.addStockIndex(stockMarket.getId(), stockIndex) == true) {
			return true;
		} else {
			return false;
		}
	}

	@Timeout
	public void timeout() {
		logger.info("Invoke.timeout STOCKindexCOLLECTOR");
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public StockMartekFacade getStockMarketFacade() {
		return stockMarketFacade;
	}

	public void setStockMarketFacade(StockMartekFacade stockMarketFacade) {
		this.stockMarketFacade = stockMarketFacade;
	}

	public List<StockMarket> getStockMarkets() {
		return stockMarkets;
	}

	public void setStockMarkets(List<StockMarket> stockMarkets) {
		this.stockMarkets = stockMarkets;
	}

}
