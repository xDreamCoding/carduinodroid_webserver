package de.xdreamcoding.desktop.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* methods for writing a *.gpx-file
* @author Lars Vogel
* @version 12.06.2012
*/

public class GPSTrack {
	
	BufferedWriter writer;
	SimpleDateFormat dateformat;
	Date date;
	File file;
	File path;
	
	// ***** GPSTrack Constructor ***************************************
			/** The constructor starts to write a *.gpx-file with all important
			 *parts like date, directory and gpx-typical strings. 
			 *
			 *@param date Reads the date of the used computer
			 *@param dateformat Format for the date
			 *@param trackfile Say how to save the *.gpx-file
			 */
	public GPSTrack(){
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy_MM_dd_HH_mm_ss" );
		String trackfile = "Track_"+dateformat.format(date)+".gpx";
		SimpleDateFormat gpx = new SimpleDateFormat("dd MMM yyyy HH:mm");
		
		File path = new File("src/gpx/");
		path.mkdirs();
		file = new File("src/gpx/",trackfile);

		try {
				file.createNewFile();
			} catch (IOException e) { e.printStackTrace(); }
		file.canWrite();
		file.canRead();
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) { e.printStackTrace(); }
		
		try {
			writer.write("\u003C"+"?xml version=\"1.0\" encoding=\"UTF-8\" ?"+"\u003E");
			writer.write(System.getProperty("line.separator"));
			writer.write("\u003C"+"gpx version=\"1.1\" creator=\"CarDuinoDroid\" "+"xmlns=\"http://www.topografix.com/GPX/1/1\"");
			writer.write(System.getProperty("line.separator"));
			writer.write("\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
			writer.write(System.getProperty("line.separator"));
			writer.write("\txsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\""+"\u003E");writer.write(System.getProperty("line.separator"));				
			writer.write("\t\u003C"+"trk"+"\u003E"+"\u003C"+"name"+"\u003E"+"Aktueller Track: "+gpx.format(date)+"\u003C"+"/name"+"\u003E");
			writer.write(System.getProperty("line.separator"));
			writer.write("\t\t\u003C"+"trkseg"+"\u003E");
			writer.write(System.getProperty("line.separator"));
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ***** Write GPX File ***************************************
			/** You need to have a typical format to save a all your values
			 *in a +.gpx-file. This method provides it.
			 * 
			 *@param Long Contains the longitude value
			 *@param Lat Contains the latitude value
			 */
	public void writegpxfile(String Long, String Lat){
	
		try {
			Date data = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-DD");
			SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
			String formatdate = date.format(data);
			String formattime = time.format(data);
			writer.write("\t\t\t\u003Ctrkpt lat=\""+Lat+"\" lon=\""+Long+"\"\u003E");
			writer.write(System.getProperty("line.separator"));
			writer.write("\t\t\t\t\u003Ctime\u003E"+formatdate+"T"+formattime+"Z"+"\u003C/time\u003E");
			writer.write(System.getProperty("line.separator"));
			writer.write("\t\t\t\u003C/trkpt\u003E" );
			writer.write(System.getProperty("line.separator"));
			writer.flush();	
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	// ***** Save GPX File ***************************************
			/** 
			 * At the end of a session u need to save your *.gpx-file
			 * because it need to have a typical format to be closed.
			 */
	public void savegpxfile(){
		try {
			writer.write("\t\t\u003C/trkseg\u003E");writer.write(System.getProperty("line.separator"));
			writer.write("\t\u003C/trk\u003E");writer.write(System.getProperty("line.separator"));
			writer.write("\u003C/gpx\u003E");writer.write(System.getProperty("line.separator"));
			writer.flush();
			writer.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

}


