package de.xdreamcoding.desktop.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import de.xdreamcoding.desktop.Controller.Controller_Computer;
import de.xdreamcoding.desktop.Model.Log;

/** Methods for working with the record toggle button.
* 
* @author Benjamin L
* @author Lars
* @version 06.06.2012.
*/
public class Record_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	JToggleButton status;
	
	/** 
	 * @param controllercomputer	used instance of Controller_Computer.
	 * @param LOG					Log which is used.
	 * @param STATUS				Record ToggleButton. 
	 */
	public Record_ActionListener (Controller_Computer controllercomputer, Log LOG, JToggleButton STATUS){
		controller_Computer = controllercomputer;
		log = LOG;
		status = STATUS;
	}
	
	/** If you want to start or stop a record, you need to send the right
	 * signal to your Android device. With a toggle button u can decide
	 * whether it has to stop or start.
	 * 
	 * @param e			Event by pressing the record ToggleButton.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (status.isSelected()){
			log.writelogfile("Sent: Start sound recording.");
			controller_Computer.soundrecording.send_start_recording();
		}
		else{
			log.writelogfile("Sent: Stop sound recording.");
			controller_Computer.soundrecording.send_stop_recording();
		}		
	}
}
