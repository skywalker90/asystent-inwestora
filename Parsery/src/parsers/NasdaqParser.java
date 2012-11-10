package parsers;

import org.jsoup.nodes.Element;

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
	protected StockIndex getStockIndex(Element index) {
		StockIndex stockindex = new StockIndex();
			
		/* name */
		stockindex.setName(index.child(1).text());
		
		/* value */
		stockindex.setValue(index.child(2).text());
		
		
		return stockindex;
	}

	
}
