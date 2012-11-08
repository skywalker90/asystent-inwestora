package parsers;

import java.util.Date;
import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class NasdaqParser extends MarketParser {
	private Date time = new Date();
	
	public NasdaqParser(String url) {
		super(url);
	}
	
	@Override
	public LinkedList<Element> getAllIndexesRows() {
		Document doc = getDocument();
		LinkedList<Element> indexes = new LinkedList<Element>();
		
		Element tbody = doc.getElementById("OtherIndicesTable").child(1);
		
		for(Element tr : tbody.children()) {
			indexes.add(tr);
		}
		
		return indexes;
	}

	@Override
	public StockIndex getStockIndex(Element index) {
		StockIndex stockindex = new StockIndex();
		
		/* symbol */
		Element h3 = index.getElementsByTag("h3").first();
		stockindex.setSymbol(h3.text());
		
		/* name */
		stockindex.setName(index.child(1).text());
		
		/* value */
		stockindex.setValue(index.child(2).text());
		
		/* date */
		stockindex.setTime(time);
		
		return stockindex;
	}
	
}
