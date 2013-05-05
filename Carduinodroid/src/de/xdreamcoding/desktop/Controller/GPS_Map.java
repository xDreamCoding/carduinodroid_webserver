package de.xdreamcoding.desktop.Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/** 
 * Creates a google-maps link with the gps coordinates
 * 
 * @author Sven Haueisen
 * @version 18.06.2012
 */

public class GPS_Map
{
	Controller_Computer controller_computer;
	String link;

	/**
	 * Needs the Controller_Computer to get access to the log
	 */
	
	public GPS_Map(Controller_Computer ControllerComputer) 
	{
		
		controller_computer = ControllerComputer;
	}
	
	/**
	 * Opens the link with the longitudes and latitudes coordinates
	 * 
	 * @param longitude
	 * @param latitude
	 */
	
	public void open_map(String longitude, String latitude)
	{
		generatelink(longitude, latitude);
		openLink();
	}
	
	/**
	 * Generates the link
	 * 
	 * @param longitude 
	 * @param latitude  
	 */

	private void generatelink(String longitude, String latitude)
	{
		 //creates a google link with the longitudes and latitudes coordinates of the current position
		link = "http://maps.google.com/maps?q=loc:" + longitude + "," + latitude;
	}
	/**
	 * Opens the link in the browser
	 * 
	 * @see Desktop
	 */
	private void openLink()
	{
	
		try {
			Desktop.getDesktop().browse(new URI(link));
			controller_computer.log.writelogfile("Google Maps im Standardbrowser aufgerufen");
		} 
		catch (IOException e) {e.printStackTrace();} 
		catch (URISyntaxException e) {e.printStackTrace();}
	} 
}