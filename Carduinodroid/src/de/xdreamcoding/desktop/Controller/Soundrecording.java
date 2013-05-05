package de.xdreamcoding.desktop.Controller;

/**
* methods for sending soundrecording signals
* 
* @author Lars Vogel
* @version 12.06.2012
*/

public class Soundrecording {
	Controller_Computer controller_computer;
	
	public Soundrecording(Controller_Computer ControllerComputer) {
		controller_computer = ControllerComputer;
	}
	
	// ***** Change Camera Type ***************************************
		/** 
		 * If you need to record any observed conversation this
		 * will be the right method for you. With this command
		 * you will be able to send a signal to start the record
		 */
	public void send_start_recording(){
		if (controller_computer.network.send_sound("2;1"))
		{controller_computer.log.writelogfile("Recording has started");}
		else{controller_computer.log.writelogfile("Recording signal wasn't sent");}
	}
	
	// ***** Change Camera Type ***************************************
			/** 
			 * A started record should be stopped. Because your
			 * memory size is limited and you don't want to listen
			 * to all senseless records
			 */
	public void send_stop_recording(){
		if (controller_computer.network.send_sound("2;0"))
		{controller_computer.log.writelogfile("Redording has stopped");}
		else{controller_computer.log.writelogfile("Recording signal wasn't sent");}
	}
}
