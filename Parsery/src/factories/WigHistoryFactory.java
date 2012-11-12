package factories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import parsers.WigHistoryParser;

import abstracts.HistoryFactory;
import abstracts.MarketParser;

public class WigHistoryFactory extends HistoryFactory{
	
	public static LinkedList<MarketParser> getParsers(String dataStart) throws ParseException{
		setPattern("yyyyMMdd");
		LinkedList<MarketParser> parsers = new LinkedList<MarketParser>();
	//	String yesterday = _getYesterday();
		String beforDateUrl = "http://gielda.wp.pl/date,";
		String afterDateUrl = ",max,20121109,sort,a0,typ,indeksy,notowania.html?ticaid=1f811&_ticrsn=5";
		String startDay;
		String dayBefore;
		String today = _getToday();
		
		startDay = today;
		
		while(_isWeekend( _getDayBefore(startDay))){
			startDay =  _getDayBefore(startDay);
			System.out.println("wczoraj byl weekend");
		}
		
		System.out.println("start data "+ startDay);
		System.out.println("target data "+ dataStart);

		while (! _getDayBefore(startDay).equals(dataStart)){
			
				dayBefore =  _getDayBefore(startDay);
				System.out.println("data danych z parsera "+ dayBefore);
				
				if(!_isWeekend(dayBefore))
					parsers.add(new WigHistoryParser(beforDateUrl + dayBefore +afterDateUrl, dayBefore));
				
				startDay = dayBefore;
			}
		

		return parsers;
	}
	

}