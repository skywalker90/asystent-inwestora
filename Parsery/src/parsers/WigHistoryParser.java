/**
 * 
 */
package parsers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import models.MarketIndex;

import org.jsoup.nodes.Element;

import abstracts.MarketParser;

/**
 * @author admin
 *
 */
public class WigHistoryParser extends MarketParser{
	
	private Date _createDate;
	private static final Set<String> IndexNamesArray = new HashSet<String>(Arrays.asList(
		     new String[] {"mWIG40", "sWIG80", "WIG", "WIG20", "WIG-CEE", "WIGdiv"}
		));
	
	public WigHistoryParser(String url, String Date) throws ParseException {
		super(url);
		this._createDate = new SimpleDateFormat("yyyyMMdd").parse(Date);
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
	protected MarketIndex getStockIndex(Element index) {
			MarketIndex stockindex = new MarketIndex();
			
			/* name */
			stockindex.setName(index.getElementsByClass("name").text());
			
			/* value */
			stockindex.setValue(index.child(2).text());
			
			/* date */
			stockindex.setTime(this._createDate);
			
			return stockindex;
	}

}
