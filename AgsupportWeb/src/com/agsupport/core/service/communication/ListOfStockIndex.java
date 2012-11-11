package com.agsupport.core.service.communication;

import java.io.Serializable;
import java.util.List;

import com.agsupport.core.jpa.model.StockIndex;

public class ListOfStockIndex implements Serializable {

	private List<StockIndex> stockIndexes;

	public ListOfStockIndex() {
	}

	public ListOfStockIndex(List<StockIndex> stockIndexes) {
		this.stockIndexes = stockIndexes;
	}

	public List<StockIndex> getStockIndexes() {
		return stockIndexes;
	}

	public void setStockIndexes(List<StockIndex> stockIndexes) {
		this.stockIndexes = stockIndexes;
	}

}
