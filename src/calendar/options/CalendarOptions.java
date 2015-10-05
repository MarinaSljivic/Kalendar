package calendar.options;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import calendar.bean.Reminder;
import calendar.data.CSVFile;

/**
 * @author Marina Sljivic
 *
 */
public class CalendarOptions {
	
	//data field that holds the last month the user entered
	private static MonthCalendar currentMonth;
	
	/**
	 * Asks a month and an year, constructs a MonthCalendar object<br>
	 * with the entered month and year. Set it to be the currentMonth.<br>
	 * Constructs a new CalendarOptions for the currentMonth.
	 */
	public CalendarOptions(){

		//the months are from 0 to 11, but the user will enter from 1 to 12, so we need -1 
		MonthCalendar mc = new MonthCalendar(enterMonth()-1,enterYear());
		//set the current month to be the month calendar and print it
		setCurrentMonth(mc);
		mc.print();
	}
	
	//getter and setter for the currentMonth
	public static MonthCalendar getCurrentMonth() {
		return currentMonth;
	}

	public static void setCurrentMonth(MonthCalendar currentMonth) {
		CalendarOptions.currentMonth = currentMonth;
	}
	
	/**
	 * Displays to the user the options (near every possible option there is a number),<br>
	 * depending on his choice launches the selected option.
	 */
	public void displayOptions(){
		int option=0;
		do{//print the options while it's not selected option 5 - exit
			System.out.println("\n\nSELECT ONE OF THE OPTIONS");
			System.out.println("1. Save a reminder for a day in this month\n"
					+ "2. See the reminders for a day in this month\n"
					+ "3. See all the reminders in this month\n"
					+ "4. See the calendar for another month\n"
					+ "5. Exit\n");
			System.out.println("Enter the number near the option: ");

			boolean inputIsOk=false;//checks the user input if it's 1,2,3,4 or 5
			do{
				try{
					inputIsOk=true;//suppose it's ok
					Scanner scan = new Scanner(System.in);
					option=Integer.parseInt(scan.next());//take the option choice
					switch (option){
						case 1:
							saveReminderForADay();
							break;
						case 2:
							checkRemindersForADay();
							break;
						case 3:
							checkRemindersCurrentMonth();
							break;
						case 4:
							seeAnotherMonth();
							break;
						case 5:
							off();
							break;
						default:
							inputIsOk=false;//if the choice is an integer but not 1,2,3,4 or 5 then the input isn't ok
							System.out.println("You entered a wrong number. Try again: ");
							break;
					}
				}catch(Exception e){//if an integer wasn't entered
					inputIsOk=false;
					System.out.println("Wrong syntax. Enter the NUMBER near the option: ");
				}
			}while(!inputIsOk);
		}while(option!=5);
	}
	
	/**
	 * Asks the user to enter a month from 1 to 12<br>
	 * while the inputed month is not ok.
	 * 
	 * @return the inputed month
	 */
	public int enterMonth(){
		Scanner scan = new Scanner(System.in);
		boolean inputIsOk=false;//checks if the entered month is valid from 1 to 12
		int month=0;//set the month to 0
		while(!inputIsOk){//while the input is not ok
			try{
				//try to take the month				
				System.out.print("Enter a month: ");
				month=Integer.parseInt(scan.next());//parse the month
				if(month>0 && month<13){//if its ok
					inputIsOk = true;//break the while 
				}
			}catch(Exception e){//handle the exceptions
				System.out.println("That month doesn't exist!");
				scan.nextLine();
				inputIsOk=false;
			}
		}
		return month;//return the month
	}
	
	/**
	 * Asks the user to enter an year, a non negative integer<br>
	 * while the inputed year is not ok.
	 * 
	 * @return the inputed year
	 */
	public int enterYear(){
		Scanner scan = new Scanner(System.in);
		boolean inputIsOk=false;//checks if the entered year is valid, not negative
		int year=0;
		while(!inputIsOk){
			try{
				//try to take the year				
				System.out.print("Enter an year: ");
				year=Integer.parseInt(scan.next());
				if(year>0){
					inputIsOk = true;//break the while 
				}
			}catch(Exception e){//handle the exceptions
				System.out.println("Wrong syntax. try again.");
				scan.nextLine();
				inputIsOk=false;
			}
		}
		return year;
	}
	
	/**
	 * Asks the user to enter a day of the current month<br>
	 * (from 1 to the last day in the month)<br>
	 * while the inputed day is not ok.
	 * 
	 * @return the inputed day
	 */
	public int enterDayInMonth(){
		Scanner scan = new Scanner(System.in);
		boolean inputIsOk=false;//checks if the entered day is valid, from 1 to the last day in the month
		int day=0;//set the day to 0
		while(!inputIsOk){//while the inputed day is not ok
			try{
				//try to take the day				
				System.out.print("Enter a day of this month: ");
				day=Integer.parseInt(scan.next());//parse the day
				
				//if the inputed day is in range 1 to the last day of the current month
				if(day>0 && day<=getCurrentMonth().getNumberOfDays()){
					inputIsOk = true;//break the while 
				}
			}catch(Exception e){//handle the exceptions
				System.out.println("Wrong syntax. try again.");
				scan.nextLine();
				inputIsOk=false;
			}
		}
		//return the day
		return day;
	}
	
	
	//methods for the options
	
	/**
	 * Asks the user to enter which day of the current month<br>
	 * he wants to save a reminder and asks to enter the note.<br>
	 * Then it creates a Reminder containing the day, current month year<br>
	 * and the note and writes it to a csv file.
	 */
	public void saveReminderForADay(){
		//take the day of the month and the note
		int day = enterDayInMonth();
		System.out.println("Enter the reminder:");
		Scanner scan = new Scanner(System.in);
		String note = scan.nextLine();
		
		//write the reminder to the csv file
		//the months are from 0 to 11, so we need +1
		CSVFile.writeTo("reminders.csv", new Reminder(day, getCurrentMonth().getMonth()+1,
				getCurrentMonth().getYear(), note));
	}
	
	/**
	 * Prints in console all the reminders for a certain day in the current month and year.
	 * @throws IOException
	 */
	public void checkRemindersForADay() throws IOException{
		int day = enterDayInMonth();
		//this list will contain all the reminders
		List<Reminder> listOfReminders = CSVFile.readReminders("reminders.csv");
		//if there is a reminder in the csv file that is on the day in this month
		//(the day,month,year of the reminder equals to the current month,year) print it
		for(Reminder r : listOfReminders){

			//the months are from 0 to 11, so we need +1
			if (r.getMonth() == getCurrentMonth().getMonth()+1
					&& r.getYear() == getCurrentMonth().getYear()
					&& r.getDay() == day){
				
				r.printInConsole();
			}
		}
	}
	
	/**
	 * Prints the reminders for this current month
	 * @throws IOException
	 */
	public void checkRemindersCurrentMonth() throws IOException{
		//this list will contain all the reminders
		List<Reminder> listOfReminders = CSVFile.readReminders("reminders.csv");
		
		//if there is a reminder in the csv file that is in this month
		//(the month(year) of the reminder equals to the current month(year)) print it
		for(Reminder r : listOfReminders){
			if (r.getMonth() == getCurrentMonth().getMonth()+1
					&& r.getYear() == getCurrentMonth().getYear()){
				r.printInConsole();
			}
		}
	}
	
	/**
	 * Asks the user to enter another month and year and prints in console the calendar for that month.
	 */
	public void seeAnotherMonth(){
		//ask to enter the new month and the year
		//create a new MonthCalendar and set it to be the current
		setCurrentMonth(new MonthCalendar(enterMonth()-1, enterYear()));
		//and print it
		getCurrentMonth().print();		
	}
	
	/**
	 * Exit from the console calendar.
	 */
	public void off(){
		System.exit(0);
	}

}
