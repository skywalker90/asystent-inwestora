package tests;

import java.text.ParseException;
import java.util.LinkedList;

import abstracts.MarketParser;
import factories.HistoryFactory;
import factories.ParserFactory;

public class Test {
	static public void main(String args[]) throws ParseException { 
	//	MarketParser[] p = ParserFactory.getParsers();
//		p[1].getAllIndexesRows();
//		System.out.println(p[2].getResults().toString());
		LinkedList<MarketParser> p = HistoryFactory.getParsers("20121102");
		for(MarketParser el : p){
			el.toString();
		}
	}
}
