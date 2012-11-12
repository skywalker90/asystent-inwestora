package tests;

import java.text.ParseException;
import java.util.LinkedList;

import abstracts.MarketParser;
import factories.DerivativeFactory;
import factories.WigHistoryFactory;
import factories.ParserFactory;

public class Test {
	static public void main(String args[]) throws ParseException { 
//		MarketParser[] p = ParserFactory.getParsers();
//		//p[1].getGetAllIndexesRows();
//		System.out.println(p[1].getResults().toString());
		LinkedList<MarketParser> p = DerivativeFactory.getParsers("20121102");
		for(MarketParser el : p){
			
			System.out.println(el.getResults().toString());
		}
	}
}
