package com.agsupport.parser.derivative;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
 
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
 
import com.agsupport.core.jpa.model.DerivativeValue;
 
public class CommodityOnlineParser extends DerivativeParser {
	public CommodityOnlineParser(String url, String name) {
		super(url);
		setStockMarketName(name);
	}
 
	@Override
	protected void getAllValuesRows() {	
		Elements divs = getDocument().getElementById("container_fix").child(2).getElementsByClass("st_yellow");
			
		for(Element div : divs) {
			String expiryDate = div.child(0).child(0).text();
			
			Boolean unique = true;
			
			for(Element index : indexes){
				if(index.child(0).child(0).text().equals(expiryDate)) {
					unique = false;
				}
			}
			
			if(unique)
				indexes.add(div);
		}
	}
 
	@Override
	protected DerivativeValue getValue(Element index) {
		DerivativeValue derValue = new DerivativeValue();
		
		derValue.setDateOfAdd(new Date());
		try {
			derValue.setExpiredDate(parseDate(index.child(0).child(0).text()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		derValue.setPrice(parsePrice(index.child(0).child(1).text()));
		
		return derValue;
	}
	
	private Date parseDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy", Locale.ENGLISH);
		
		return sdf.parse(date);
	}
	
	private Double parsePrice(String price) {		
		//price = price.replaceAll("\\(.*\\)", "");
				
		return Double.parseDouble(price);
	}
}