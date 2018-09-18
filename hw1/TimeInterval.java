package Calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This is the time interval class that represents Event time
 * @author WCISA
 *
 */
public class TimeInterval 
{
	public LocalTime startingTime; 
	public LocalTime endingTime;
	
	/**
	 * This is the time interval class constructor that represents Event time
	 *@param pStartTime
	 *@param pEndTime
	 */
	public TimeInterval(LocalTime pStartTime, LocalTime pEndTime)
	{
		startingTime = pStartTime;
		endingTime = pEndTime;
	}
	
	/**
	 * This is the method that finds conflicts between time appointments
	 *@param t
	 *
	 */
	public boolean conflict(TimeInterval t)
	{
		if(this.startingTime.isBefore(t.endingTime) && this.startingTime.isAfter(t.startingTime))
		{
			return true;
		}
		if(this.endingTime.isBefore(t.endingTime) && this.endingTime.isAfter(t.startingTime))
		{
			return true;
		}
		if(this.startingTime.isBefore(t.endingTime) && this.endingTime.isAfter(t.startingTime))
		{
			return true;
		}
		if(this.startingTime.isAfter(t.endingTime) && this.endingTime.isBefore(t.startingTime))
		{
			return true;
		}
		else 
			return false;
	}
	
/*	public boolean conflict(TimeInterval t)
	{
		if(this.startingTime <= (t.endingTime) && this.startingTime >= (t.startingTime))
		{
			return true;
		}
		if(this.endingTime <= (t.endingTime) && this.endingTime >= (t.startingTime))
		{
			return true;
		}
		if(this.startingTime <= (t.endingTime) && this.endingTime >= (t.startingTime))
		{
			return true;
		}
		if(this.startingTime >= (t.endingTime) && this.endingTime <= (t.startingTime))
		{
			return true;
		}
		else 
			return false;
	}
*/
	
	/**
	 * This is s special method of to use for time
	 */
	public LocalTime getStartingTime() {
		return startingTime;
	}
	/**
	 * This method finds starting time
	 * @return startingTime
	 */
	public void setStartingTime(LocalTime startingTime) {
		this.startingTime = startingTime;
	}
	/**
	 * This method finds special ending time
	 * @return endingTime
	 */
	public LocalTime getEndingTime() {
		return endingTime;
	}
	/**
	 * This method finds ending time
	 * @return endingTime
	 */
	public void setEndingTime(LocalTime endingTime) {
		this.endingTime = endingTime;
	}

}