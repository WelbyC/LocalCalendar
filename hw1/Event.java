package Calendar;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.time.*;

/**
 * @author WelbyChan
 * @version 1.0 9/14/2018
 * Event class is a skeleton for event creating and managing
 * */
public class Event

{

	private String name;

	private String date;
	public java.util.Date rDate;
	public TimeInterval time;
	//public LocalTime lTime;
	public LocalDate lDate;

	
	/**
	 * This creates a new event when the parameters are filled
	 *@param pName
	 *@param pDatee
	 *@param pT
	 * */
	public Event(String pName, String pDate, TimeInterval pT)
	{
		
		
		name = pName;

		date = pDate;

		time = pT;	
		
		time.startingTime = pT.getStartingTime();
		time.endingTime = pT.getEndingTime();
		
		
		
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

 

         lDate = LocalDate.parse(date, formatter);

	

	
			
		
	}
	/**
	 * returns a String name from event
	 *@param return name
	 * */
	public String getName()
	{
		return name;
	}
	/**
	 * returns a String date from event
	 *@param return name
	 * */
	public String getDate()
	{
		return date;
	}
	/**
	 * returns a LocalDate type date from event
	 *@param return lDate
	 * */
	public LocalDate getlDate()
	{
		return lDate;
	}
	/**
	 * returns the time interval of event
	 *@param return time
	 * */
	public TimeInterval getTime()
	{
		return time;
	}



	public static Comparator<Event> ComparatorDate  = new Comparator<Event>() {

		@Override
		public int compare(Event event1, Event event2) {

			LocalDate date1 = event1.getlDate();
			LocalDate date2 = event2.getlDate();

			return date1.compareTo(date2);

		}

	};
	public static Comparator<Event> ComparatorTime  = new Comparator<Event>() {

		@Override
		public int compare(Event event1, Event event2) {

			LocalTime time1 = event1.getTime().getStartingTime();
			LocalTime time2 = event2.getTime().getStartingTime();

			return time1.compareTo(time2);

		}

	};



		
		

}






