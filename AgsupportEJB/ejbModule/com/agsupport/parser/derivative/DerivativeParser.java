package com.agsupport.parser.derivative;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.agsupport.core.jpa.model.DerivativeValue;

public abstract class DerivativeParser {
	private Document _document = null;
	private String _url = null;
	private LinkedList<DerivativeValue> _stockValuesList = new LinkedList<DerivativeValue>();
	private Map<String, DerivativeValue> _map = new HashMap<String, DerivativeValue>(); 
	
	protected LinkedList<Element> indexes = new LinkedList<Element>();
	protected String stockMarketName; 
	
	public DerivativeParser(String url) {
		this._url = url;
	}
	
	public LinkedList<DerivativeValue> getResults() {
		try {
			this.getAllValuesRows();
			this.parse();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("nie ma danych");
		}
		
		return this._stockValuesList;
	}
	
	protected Element getDataContainerById(String Id){
		return  getDocument().getElementById(Id);
	}
	
	protected Document getDocument() {
		if(_document != null) {
			return _document;
		}
		
		Connection connection = Jsoup.connect(_url);
		
		try {
			_document = connection.post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return _document;
	}
	
	private void parse() {
		for(Element el : this.indexes) {
			DerivativeValue indexToAdd = getValue(el);
			this._stockValuesList.add(indexToAdd);
		}
	}
	
	public String getStockMarketName() {
		return stockMarketName;
	}

	public void setStockMarketName(String stockMarketName) {
		this.stockMarketName = stockMarketName;
	}
	
	public Map<String, DerivativeValue> getStockIndexList() {
		/* don't blame me, it's all because of Radoslaw's ridiculous fantasy ;-) */
		if(_stockValuesList.isEmpty()) {
			getResults();
		}
		
		for(DerivativeValue si : _stockValuesList) {
			_map.put(getStockMarketName(), si);
		}
		
		return _map;
	}
	
	protected abstract void getAllValuesRows();
	protected abstract DerivativeValue getValue(Element index);
}
