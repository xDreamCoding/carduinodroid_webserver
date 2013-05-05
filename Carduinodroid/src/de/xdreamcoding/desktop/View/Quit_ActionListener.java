package de.xdreamcoding.desktop.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.xdreamcoding.desktop.Model.GPSTrack;
import de.xdreamcoding.desktop.Model.Log;

/** Methods for working with the quit menuitem.
* @author Benjamin L
* @author Lars
* @version 12.06.2012.
*/
public class Quit_ActionListener implements ActionListener {
	Log log;
	GPSTrack gpstrack;
	
	/** 
	 * @param LOG					Log which is used.
	 * @param GPS_Track				Used gpstrack.
	 */
	public Quit_ActionListener (Log LOG, GPSTrack GPS_Track)
	{
		log = LOG;
		gpstrack = GPS_Track;
	}
	
	/** 
	 * Before the whole programm is closed by "system.exit" it is 
	 * important to save the log and *.gpx-file by their own method.
	 * After this you can use the track file on certain platforms.
	 * 
	 * @param e			Event by pressing the quit menuitem.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		gpstrack.savegpxfile();
		log.writelogfile("CarDuinoDroid closed.");
		System.exit(0);
	}
}
