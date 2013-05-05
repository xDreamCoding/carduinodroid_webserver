package de.xdreamcoding.desktop.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import de.xdreamcoding.desktop.Controller.Controller_Computer;

/** Class for showing position of the car. 
* 
* @author Benjamin L
* @author Lars
* @version 09.06.2012.
*/
public class Map_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	JLabel latitude;
	JLabel longitude;
	
	/** 
	 * @param controllercomputer	Used instance of Controller_Computer.
	 * @param LATITUDE				Latitude of actual position of the car.
	 * @param LONGITUDE				LONGITUDE of actual position of the car. 
	 */
	public Map_ActionListener (Controller_Computer controllercomputer, JLabel LATITUDE, JLabel LONGITUDE){
		controller_Computer = controllercomputer;
		latitude = LATITUDE;
		longitude = LONGITUDE;
	}
	
	/** 
	 * This method will open the default browser of your computer
	 * and show the coordinates on google maps.
	 * 
	 * @param e			Event by pressing the map button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		controller_Computer.gps_map.open_map(latitude.getText(), longitude.getText());
	}

}