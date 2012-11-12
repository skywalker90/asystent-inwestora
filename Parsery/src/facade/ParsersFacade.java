package facade;

import java.text.ParseException;
import java.util.LinkedList;

import abstracts.MarketParser;
import factories.DerivativeFactory;
import factories.WigHistoryFactory;

public class ParsersFacade {	
	public static LinkedList<MarketParser> getWigParsers(String dataStart) {
		try {
			return WigHistoryFactory.getParsers(dataStart);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static LinkedList<MarketParser> getDerivativeParsers(String dataStart) {
		try {
			return DerivativeFactory.getParsers(dataStart);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
