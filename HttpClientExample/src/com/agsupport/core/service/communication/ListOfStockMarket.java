package com.agsupport.core.service.communication;

import java.io.Serializable;
import java.util.List;

import com.agsupport.core.jpa.model.StockMarket;

public class ListOfStockMarket implements Serializable {

	private List<StockMarket> stockMarkets;

	public ListOfStockMarket() {
	}

	public ListOfStockMarket(List<StockMarket> stockMarkets) {
		this.stockMarkets = stockMarkets;
	}

	public List<StockMarket> getStockMarkets() {
		return stockMarkets;
	}

	public void setStockMarkets(List<StockMarket> stockMarkets) {
		this.stockMarkets = stockMarkets;
	}

}
