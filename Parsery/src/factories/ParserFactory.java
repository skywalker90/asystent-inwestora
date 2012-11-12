package factories;

import abstracts.MarketParser;
import parsers.NasdaqParser;
import parsers.WigParser;


public class ParserFactory {
	public static MarketParser[] getParsers(){
		 MarketParser[] parsers={
				new NasdaqParser("http://www.nasdaq.com/markets/indices/sector-indices.aspx"),
				new WigParser("http://gielda.wp.pl/indeksy.html")		
				};
		
		 return parsers;
	}
}
