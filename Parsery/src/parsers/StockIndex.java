package parsers;

import java.util.Date;

public class StockIndex {
	private Date time;
	private String symbol;
	private String name;
	private String value; /* @TODO: change to Double? */
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String toString() {
		String str = "Symbol: " + symbol + "\n" +
			"Name: " + name + "\n" +
			"Value: " + value + "\n" +
			"Time: " + time.toString() + "\n";
		
		return str;
	}
}
