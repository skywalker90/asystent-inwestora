package parsers;

import org.jsoup.nodes.Element;

public class Test {
	static public void main(String args[]) { 
		NasdaqParser p = new NasdaqParser("http://www.nasdaq.com/markets/indices/sector-indices.aspx");
		
		for(Element el : p.getAllIndexesRows()) {
			System.out.println(p.getStockIndex(el).toString());
		}
	}
}
