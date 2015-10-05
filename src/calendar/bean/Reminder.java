package calendar.bean;

/**
 * @author Marina Sljivic
 *
 */
public class Reminder {
	private int day, month, year;
	private String note;
	
	/**
	 * Constructs a Reminder with the specified day,month,year and note.
	 * 
	 * @param day int
	 * @param month int
	 * @param year int
	 * @param note String
	 */
	public Reminder(int day, int month, int year, String note){
		setDay(day);
		setMonth(month);
		setYear(year);
		setNote(note);
	}
	
	//getters and setters for the day, month, year and note of the reminder
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	//overriding the toString() method for easier writing in the csv file
	
	@Override
	public String toString(){
		return getDay()+","+getMonth()+","+getYear()+","+getNote();
	}
	
	/**
	 * Prints in console the reminder i format:<br>
	 * <br>
	 * DD/MM/YYYY<br>
	 * note<br>
	 */
	public void printInConsole(){
		System.out.println("__________________________________");
		System.out.println(getDay()+"/"+getMonth()+"/"+getYear()+"\n"
				+getNote());
		System.out.print("__________________________________\n");
	}
	
}
