package abstracts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class HistoryFactory {
	
	protected static String pattern;
	
	protected static void  setPattern(String _pattern){
		pattern = _pattern;
	} 
	
	protected static String _getYesterday(){
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		cal.add(Calendar.DATE, -1);
		System.out.println("Wczoraj byl: "+ dateFormat.format(cal.getTime()));
		return dateFormat.format(cal.getTime());
	}
	
	protected static String _getToday(){
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		System.out.println("Dzisiaj jest: "+ dateFormat.format(cal.getTime()));

		return 	dateFormat.format(cal.getTime());
	}
	
	protected static String _getDayBefore(String Date) throws ParseException{
		Date date = new SimpleDateFormat(pattern).parse(Date);
		DateFormat dateFormat = new SimpleDateFormat(pattern);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		
		System.out.println("Dzien wczesniej byl: "+ dateFormat.format(cal.getTime()));

		return dateFormat.format(cal.getTime());
	}
	
	protected static Boolean _isWeekend(String Date) throws ParseException{
		Date date = new SimpleDateFormat(pattern).parse(Date);
		Calendar calc = Calendar.getInstance();
		calc.setTime(date);
		int dayOfWeek =  calc.get(Calendar.DAY_OF_WEEK);
		if(dayOfWeek == 7 || dayOfWeek == 1){
			System.out.println("Jest weekend");

		}
		return (dayOfWeek == 7 || dayOfWeek == 1);
		
	}
}
