package factories;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import parsers.WigHistoryParser;

import abstracts.MarketParser;

public class HistoryFactory {
	public static LinkedList<MarketParser> getParsers(String dataStart) throws ParseException{
		LinkedList<MarketParser> parsers = new LinkedList<MarketParser>();
		String yesterday = _getYesterday();
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
	
	private static String _getYesterday(){
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		cal.add(Calendar.DATE, -1);
		System.out.println("Wczoraj byl: "+ dateFormat.format(cal.getTime()));
		return dateFormat.format(cal.getTime());
	}
	
	private static String _getToday(){
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		System.out.println("Dzisiaj jest: "+ dateFormat.format(cal.getTime()));

		return 	dateFormat.format(cal.getTime());
	}
	
	private static String _getDayBefore(String Date) throws ParseException{
		Date date = new SimpleDateFormat("yyyyMMdd").parse(Date);
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		
		System.out.println("Dzien wczesniej byl: "+ dateFormat.format(cal.getTime()));

		return dateFormat.format(cal.getTime());
	}
	
	private static Boolean _isWeekend(String Date) throws ParseException{
		Date date = new SimpleDateFormat("yyyyMMdd").parse(Date);
		Calendar calc = Calendar.getInstance();
		calc.setTime(date);
		int dayOfWeek =  calc.get(Calendar.DAY_OF_WEEK);
		if(dayOfWeek == 7 || dayOfWeek == 1){
			System.out.println("Jest weekend");

		}
		return (dayOfWeek == 7 || dayOfWeek == 6);
		
	}
}