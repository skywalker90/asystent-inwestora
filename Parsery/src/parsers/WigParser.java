package parsers;

import org.jsoup.nodes.Element;

public class WigParser extends MarketParser {

	public WigParser(String url) {
		super(url);
	}

	@Override
	protected void getAllIndexesRows() {
		Element tbody = this.getDataContainerById("gpwRyn").getElementsByTag("tbody").first();
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
