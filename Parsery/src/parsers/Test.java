package parsers;

public class Test {
	static public void main(String args[]) { 
		MarketParser[] p = ParserFactory.getParsers();
		System.out.println(p[2].getResults().toString());
	}
}
