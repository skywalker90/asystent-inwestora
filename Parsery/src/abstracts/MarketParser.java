package abstracts;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import parsers.StockIndex;

public abstract class MarketParser {
	//TODO co z tym warunkiem godzinnym, sprawdzane wewnatrz parsera?
	private Document _document = null;
	private String _url = null;
	private LinkedList<StockIndex> _stockIndexesList;
	
	protected LinkedList<Element> indexes;
	
	/**
	 * @param url
	 */
	public MarketParser(String url) {
		this._url = url;
		this.indexes = new LinkedList<Element>();
		this._stockIndexesList = new LinkedList<StockIndex>();
	}
	
	/**
	 * @return
	 */
	public LinkedList<StockIndex> getResults(){
		try{
			this.getAllIndexesRows();
			this.parse();
		}
		catch(Exception e){
			System.out.println("nie ma danych");
		}
		
		return this._stockIndexesList;
	}
	
	protected Element getDataContainerById(String Id){		
		return  getDocument().getElementById(Id);
	}
	
	protected Document getDocument() {
		if(_document != null) {
			return _document;
		}
		
		Connection connection = Jsoup.connect(_url);
		
		try {
			_document = connection.post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return _document;
	}
	
	private void parse(){
		for(Element el : this.indexes){
			this._stockIndexesList.add(getStockIndex(el));
		}
	}
	
	protected abstract void getAllIndexesRows();
	protected abstract StockIndex getStockIndex(Element index);

}
