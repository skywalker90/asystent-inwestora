package parsers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.DerivativeIndex;

import org.jsoup.nodes.Element;

import abstracts.MarketParser;

public class DerivativeParser extends MarketParser{
	private Date _createDate;
	
	public DerivativeParser(String url, String date) throws ParseException {
		super(url);
		this._createDate = new SimpleDateFormat("yyyyMMdd").parse(date);	
	}
	
	private Date _parseExpireDate(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM.yy");
		String month, year;
		
		switch(str.charAt(4)) {
			case 'H':
				month = "03";
				break;
			case 'M':
				month = "06";
				break;
			case 'U':
				month = "09";
				break;
			case 'Z':
				month = "12";
				break;
			default:
				throw new ParseException("Nieoczekiwana literka daty", 0);	
		}
		
		year = str.substring(5, 7);
		
		return sdf.parse(month + "." + year);
	}
	
	@Override
	protected void getAllIndexesRows() {
		Element tbody = this.getDocument().getElementsByClass("tab_fld").first().getElementsByTag("tbody").first();
		int counter = 0;
		for(Element tr : tbody.children()) {
			if(counter>1)	
				this.indexes.add(tr);		
			counter++;
		}
	}

	@Override
	protected DerivativeIndex getStockIndex(Element index) {
		DerivativeIndex derIndex = new DerivativeIndex();
		String name;
		
		/* data wpisu*/
		derIndex.setDateOfAdd(this._createDate);
		
		name = index.child(1).text();
		
		derIndex.setName(name);
		try {
			derIndex.setExpiredDate(_parseExpireDate(name));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return derIndex;
	}

}
