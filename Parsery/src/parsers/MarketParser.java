package parsers;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public abstract class MarketParser {
	private Document document = null;
	private String url = null;
	
	public MarketParser(String url) {
		this.url = url;
	}
	
	protected Document getDocument() {
		if(document != null) {
			return document;
		}
		
		Connection connection = Jsoup.connect(url);
		
		try {
			document = connection.post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return document;
	}
	
	public abstract LinkedList<Element> getAllIndexesRows();
	
	public abstract StockIndex getStockIndex(Element index);
}
