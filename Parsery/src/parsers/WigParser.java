package parsers;

import models.MarketIndex;

import org.jsoup.nodes.Element;

import abstracts.MarketParser;

public class WigParser extends MarketParser {

	public WigParser(String url) {
		super(url);
	}

	@Override
	protected void getAllIndexesRows() {
		Element tbody = this.getDataContainerById("gpwRyn").getElementsByTag("tbody").first();
		int counter = 0;
		for(Element tr : tbody.children()) {
			if(counter > 1 && counter < 11 && tr.text().contains("WIG")){
			this.indexes.add(tr);
		//	System.out.println(tr.text());
			}
			counter++;
		}
	}

	@Override
	protected MarketIndex getStockIndex(Element index) {
		MarketIndex stockindex = new MarketIndex();
		
		/* name */
		stockindex.setName(index.child(0).text());
		
		/* value */
		stockindex.setValue(index.child(2).text());
		
		
		return stockindex;
	}	
	
}
