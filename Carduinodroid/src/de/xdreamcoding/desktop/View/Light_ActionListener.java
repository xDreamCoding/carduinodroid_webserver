package de.xdreamcoding.desktop.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import de.xdreamcoding.desktop.Controller.Controller_Computer;
import de.xdreamcoding.desktop.Model.Log;

/** Methods for working with the light toggle button.
*
* @author Benjamin L
* @author Lars
* @version 06.06.2012.
*/
public class Light_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	JToggleButton status;
	
	/**  
	 * @param controllercomputer	Used instance of Controller_Computer.
	 * @param LOG					Log which is used.
	 * @param STATUS				ToggleButton for switching light on/off.
	 */
	public Light_ActionListener (Controller_Computer controllercomputer, Log LOG, JToggleButton STATUS)
	{
		controller_Computer = controllercomputer;
		log = LOG;
		status = STATUS;
	}
	
	/** After the button was pressed, the change Event shell start. 
	 * The light will be switched off by a "0" or switched on by a "1"
	 * 
	 * @param e			Event by switching the status of ToggleButton for light.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (status.isSelected()){
			log.writelogfile("Sent: Switch camera light on.");
			controller_Computer.camera_settings.send_switch_light("1");
		}
		else{
			log.writelogfile("Sent: Switch camera light off.");
			controller_Computer.camera_settings.send_switch_light("0");
		}
		
	}

}
