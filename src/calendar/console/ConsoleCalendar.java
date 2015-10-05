package calendar.console;

import calendar.options.CalendarOptions;

/**
 * @author Marina Sljivic
 *
 */
public class ConsoleCalendar {

	public static void main(String[] args) {
		//create a new CalendarOptions and display the options in console
		CalendarOptions co = new CalendarOptions();
		co.displayOptions();
	}

}
