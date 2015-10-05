package calendar.options;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * @author Marina Sljivic
 *
 */
public class MonthCalendar {
	private int month, year;
	
	public MonthCalendar(int month,int year){
		setMonth(month);
		setYear(year);
	}
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return a Calendar object that is set to the first day in the month of the year
	 */
	public Calendar getCalendar(){
		//set the calendar to the first day in the month and return it
		return new GregorianCalendar(getYear(),getMonth(),1);
	}
	
	/**
	 * Prints a calendar of the month of the year, the days are in week-order.
	 */
	public void print(){
		//what number the 1.day in month is in the week, 1 if it is Sunday...7 if it is Saturday
		int dayInTheWeek = getCalendar().get(Calendar.DAY_OF_WEEK);
		
		//set the format for Month Year
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMMMMMM YYYY");
		
		//print the month, year and the names of the week days
		System.out.printf("\n\t  "+sdf.format(getCalendar().getTime())
				+"\n__________________________________"
				+"\nSun  Mon  Tue  Wed  Thu  Fri  Sat\n");

		//print blank space,"jump the days" until we arrive to the day in the week for the first day in the month
		for(int j=1;j<dayInTheWeek;j++){
			System.out.printf("     ");
		}
		//print the days of the month
		for(int i=0; i<getNumberOfDays(); i++){
			if(i<9){
				System.out.printf((i+1)+"    ");
			}else{
				System.out.printf((i+1)+"   ");
			}
			//when the current i-day is a Saturday go to new line
			if((dayInTheWeek + i) % 7 == 0) System.out.printf("\n");
		}
	}
	
	/**
	 * @return the number of days in this month
	 */
	public int getNumberOfDays(){
		return getCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);		
	}
	
}
