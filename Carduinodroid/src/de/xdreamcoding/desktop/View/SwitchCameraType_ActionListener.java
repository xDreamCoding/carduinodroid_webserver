package de.xdreamcoding.desktop.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.xdreamcoding.desktop.Controller.Controller_Computer;
import de.xdreamcoding.desktop.Model.Log;

/** Methods for working with camera menuitems.
* @author Benjamin L
* @author Lars
* @version 12.06.2012.
*/
public class SwitchCameraType_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	boolean camera;
	
	/** 
	 * @param controllercomputer	Used instance of Controller_Computer.
	 * @param LOG					Log which is used.
	 * @param CAMERA				Boolean which camera should be activated.
	 */
	public SwitchCameraType_ActionListener (Controller_Computer controllercomputer, Log LOG, boolean CAMERA)
	{
		controller_Computer = controllercomputer;
		log = LOG;
		camera = CAMERA;
		}
	
	/** 
	 * It enables the chosen camera (front or back). Sometimes it could be
	 * helpful to see what is behind you.
	 * 
	 * @param e			Event by pressing the frontcamera or backcamera RadioButtonMenuItem.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (camera) {
			log.writelogfile("Sent: Enable frontcamera.");
			controller_Computer.camera_settings.send_change_camera("0");
		}
		else {
			log.writelogfile("Sent: Enable backcamera.");
			controller_Computer.camera_settings.send_change_camera("1");
		}
	}
}
