package de.xdreamcoding.desktop.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.xdreamcoding.desktop.View.GUI_Computer;

/**
* methods for sending camera changes
* @author Lars Vogel
* @version 12.06.2012
*/

public class Log {
//	GUI_Computer gui_computer;
	BufferedWriter writer;
	SimpleDateFormat dateformat;
	Date date;
	File file;
	File path;
	
	// ***** GPSTrack Constructor ***************************************
				/**	The constructor starts to write a *.txt-file with all important
				 *information about the chosen functions as well as sent signals.  
				 * 
				 *@param date Reads the date of the used computer
				 *@param dateformat Format for the date
				 *@param logfile Say how to save the log file
				 */
	public Log(/**GUI_Computer GUI_computer**/){
//		gui_computer = GUI_computer;
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy_MM_dd_HH_mm_ss" );
		String logfile = "Log_"+dateformat.format(date)+".txt";
		
		File path = new File("src/Logs/");
		path.mkdirs();
		file = new File("src/Logs/",logfile);

		try {
				file.createNewFile();
			} catch (IOException e) { e.printStackTrace(); }
		file.canWrite();
		file.canRead();
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	// ***** Write Log File ***************************************
				/** All Log entries will be imported by the same format.
				 *First you will get an information about the date and time
				 *and then a short explanation about the function.
				 *
				 *@param line Contains the string which will be include
				 */
	public void writelogfile(String line){
		try {
			Date data = new Date();
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss" );
			String entry = df.format(data)+" "+line;
			writer.write(entry,0,entry.length());
			writer.write(System.getProperty("line.separator"));
			writer.flush();
			write_Live_Log(entry);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	// ***** Write Log File ***************************************
	/** 
	 *If you choose to close the application, it is important to
	 *do the same thing with our log file. Without this method it
	 *can happen that the *.txt-file is unreadable.
	 */
	public void savelogfile(){
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	// ***** Write Log File ***************************************
		/** 
		 *The log is written in a separate file but the user would
		 *like to see all information right in front of him. This
		 *method put all entries at the same time in our live log
		 *on the gui.
		 */
//	private void write_Live_Log(String Text){
//		if(gui_computer.Live_Log != null)
//		gui_computer.Live_Log.setText(gui_computer.Live_Log.getText()+"\n"+Text);
//	}

	private void write_Live_Log(String msg) {
		System.out.println(msg);
	}
}


