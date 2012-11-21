package zpi.asystent;

public class Main {
	public static void main(String[] args) {
		
		StockModel stockModel = new StockModel();
		new StockController(stockModel);		
	}
}
