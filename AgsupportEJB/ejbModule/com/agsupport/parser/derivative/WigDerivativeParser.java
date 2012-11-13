package com.agsupport.parser.derivative;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jsoup.nodes.Element;

import com.agsupport.core.jpa.model.DerivativeValue;

public class WigDerivativeParser extends DerivativeParser {
	private Date _createDate;
	
	public WigDerivativeParser() {
		super("http://gielda.wp.pl/typ,kontraktyiji_kontrakty_indeksowe,notowania.html");
	}
	
	public WigDerivativeParser(String url, String date) throws ParseException {
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
	protected void getAllValuesRows() {
		Element tbody = this.getDocument().getElementsByClass("tab_fld").first().getElementsByTag("tbody").first();
		int counter = 0;
		for(Element tr : tbody.children()) {
			if(counter > 1 && counter < 6)	
				this.indexes.add(tr);		
			counter++;
		}
	}

	@Override
	protected DerivativeValue getValue(Element index) {
		DerivativeValue derIndex = new DerivativeValue();
		String name;
		
		derIndex.setDateOfAdd(this._createDate);
		
		name = index.child(1).text();
		
		derIndex.setPrice(parsePrice(index.child(2).text()));
		derIndex.setDateOfAdd(new Date());
		
		/* todo: na pewno o to chodzilo? */
		switch(name.charAt(2)) {
			case '2':
				setStockMarketName("WIG20");
				break;
			case '4':
				setStockMarketName("WIG40");
				break;
		}
	
		try {
			derIndex.setExpiredDate(_parseExpireDate(name));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return derIndex;
	}

	private Double parsePrice(String str) {
		str = str.replaceAll(" ", "");
		str = str.replaceAll(",", ".");
		
		return Double.parseDouble(str);
	}
}
