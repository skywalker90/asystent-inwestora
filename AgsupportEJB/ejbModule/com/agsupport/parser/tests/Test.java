package com.agsupport.parser.tests;

import java.text.ParseException;
import java.util.LinkedList;

import com.agsupport.parser.derivative.DerivativeParser;
import com.agsupport.parser.derivative.WigDerivativeParser;
import com.agsupport.parser.factories.WigDerivativeHistoryFactory;
import com.agsupport.parser.factories.WigHistoryFactory;
import com.agsupport.parser.index.IndexParser;


public class Test {
	static public void main(String args[]) throws ParseException {
		// Przyk³ad u¿ycia dla wartoœci bie¿¹cych
		WigDerivativeParser p3 = new WigDerivativeParser();
		
		System.out.println(p3.getStockIndexList().toString());
		
		// Przyk³ad u¿ycia dla danych historycznych - u¿ycie factory
		
		LinkedList<IndexParser> p = WigHistoryFactory.getParsers("20121102");
		for(IndexParser el : p){			
			System.out.println(el.getStockIndexList().toString());
		}
	
		LinkedList<DerivativeParser> p2 = WigDerivativeHistoryFactory.getParsers("20121102");
		for(DerivativeParser el : p2){			
			System.out.println(el.getStockIndexList().toString());
		}
	}
}
