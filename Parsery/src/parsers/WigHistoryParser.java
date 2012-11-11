/**
 * 
 */
package parsers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;

import abstracts.MarketParser;

/**
 * @author admin
 *
 */
public class WigHistoryParser extends MarketParser{
	

	private static final Set<String> IndexNamesArray = new HashSet<String>(Arrays.asList(
		     new String[] {"mWIG40", "sWIG80", "WIG", "WIG20", "WIG-CEE", "WIGdiv"}
		));
	
	public WigHistoryParser(String url) {
		super(url);
	}

	@Override
	protected void getAllIndexesRows() {
		Element tbody = this.getDocument().getElementsByClass("tab_fld").first().getElementsByTag("tbody").first();
		for(Element tr : tbody.children()) {
			if(IndexNamesArray.contains(tr.getElementsByClass("name").text())){
			this.indexes.add(tr);
			}
		}
	}

	@Override
	protected StockIndex getStockIndex(Element index) {
			StockIndex stockindex = new StockIndex();
			
			/* name */
			stockindex.setName(index.getElementsByClass("name").text());
			
			/* value */
			stockindex.setValue(index.child(2).text());
			
			
			return stockindex;
	}

}