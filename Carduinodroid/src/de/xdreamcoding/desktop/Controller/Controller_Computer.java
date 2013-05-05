package de.xdreamcoding.desktop.Controller;

import de.xdreamcoding.desktop.Model.GPSTrack;
import de.xdreamcoding.desktop.Model.Log;
import de.xdreamcoding.desktop.View.GUI_Computer;
/** Class to include the controller classes. 
* 
* @author Felix L
* @version 17.06.2012.
*/
public class Controller_Computer {
	public Log log;
	public GPSTrack gpstrack;
	public Sound_Output sound_output;
	public Camera_Settings camera_settings;
	public Camera_Picture camera_picture;
	public Car_Controller car_controller;
	public Packagedata packagedata;
	public GPS_Map gps_map;
	public Soundrecording soundrecording;
	public Network network;
//	private GUI_Computer gui_computer;
	
	/** The constructor is a very important part of the facade pattern.
	 * The facade is an object that provides a simplified interface to a larger body of code, such as a class library.
	 *  
	 * @param GUI_computer		includes an instance of GUI_computer
	 * @param LOG 				includes an instance of LOG
	 */
	public Controller_Computer(Log LOG, /**GUI_Computer GUI_computer,**/ GPSTrack gpsTrack) {
//		gui_computer = GUI_computer;
		log = LOG;
		gpstrack = gpsTrack;
		camera_picture=new Camera_Picture(this);
		packagedata=new Packagedata(this);
		gps_map=new GPS_Map(this);
		network=new Network(packagedata,camera_picture);
		sound_output=new Sound_Output(this);
		camera_settings=new Camera_Settings(this);
		car_controller=new Car_Controller(this);
		soundrecording=new Soundrecording(this);
	}

}
