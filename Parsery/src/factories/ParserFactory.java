package factories;

import abstracts.MarketParser;
import parsers.NasdaqParser;
import parsers.WigHistoryParser;
import parsers.WigParser;


public class ParserFactory {
	public static MarketParser[] getParsers(){
		 MarketParser[] parsers={
				new NasdaqParser("http://www.nasdaq.com/markets/indices/sector-indices.aspx"),
				new WigParser("http://gielda.wp.pl/indeksy.html"),
				new WigHistoryParser("http://gielda.wp.pl/date,20121108,max,20121109,sort,a0,typ,indeksy,notowania.html?ticaid=1f811&_ticrsn=5")
		};
		
		 return parsers;
	}
}
