package calendar.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import calendar.bean.Reminder;

/**
 * Contains methods that read Reminders and write Reminders to a csv file.
 * 
 * @author Marina Sljivic
 *
 */
public class CSVFile {
	
	/**
	 * Writes to a filename.csv file the reminder
	 * @param filename String
	 * @param reminder Reminder
	 */
	public static void writeTo(String filename, Reminder reminder){
		PrintWriter pw=null;
		try{
			pw=new PrintWriter(new FileOutputStream(new File(filename),true));
			pw.write(reminder+"\n");//just append to the file the reminder
					
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			pw.close();
		}
	}
	
	/**
	 * @param filename String
	 * @return a list of reminders from the filename.csv file
	 * @throws IOException
	 */
	public static List<Reminder> readReminders(String filename) throws IOException{
		BufferedReader br=null;
		List<Reminder> remindersList=new ArrayList<>();
			try{
				br=new BufferedReader(new FileReader(filename));
				String line;
				while((line=br.readLine())!=null){
					String[] reminderArray=line.split(",");
					//parse the reminder's day, month and year
					Reminder u=new Reminder(Integer.parseInt(reminderArray[0]),Integer.parseInt(reminderArray[1]),
							Integer.parseInt(reminderArray[2]),reminderArray[3]);
					remindersList.add(u);
				}
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				br.close();
			}
		return remindersList;
	}
}
