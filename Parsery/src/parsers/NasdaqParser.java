package parsers;

import models.MarketIndex;

import org.jsoup.nodes.Element;

import abstracts.MarketParser;

public class NasdaqParser extends MarketParser {

	public NasdaqParser(String url) {
		super(url);
	}
	
	@Override
	protected void getAllIndexesRows() {	
		Element tbody = getDataContainerById("OtherIndicesTable").child(1);
		for(Element tr : tbody.children()) {
			this.indexes.add(tr);
		}
	}

	@Override
	protected MarketIndex getStockIndex(Element index) {
		MarketIndex stockindex = new MarketIndex();
			
		/* name */
		stockindex.setName(index.child(1).text());
		
		/* value */
		stockindex.setValue(index.child(2).text());
		
		
		return stockindex;
	}

	
}
