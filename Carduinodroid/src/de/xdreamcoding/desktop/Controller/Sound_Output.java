package de.xdreamcoding.desktop.Controller;

/**
* methods for sending sound output signal
* 
* @author Lars Vogel
* @version 12.06.2012
*/

public class Sound_Output {
	Controller_Computer controller_computer;
	
	public Sound_Output(Controller_Computer ControllerComputer){
		controller_computer = ControllerComputer;
	}
	
	// ***** Send sound signal ***************************************
	/**
	 * Sending a signal with the help of a socket. If someone likes to
	 * put in other sounds, he can work with SoundID.
	 * @param SoundID It is the number of sound u want to play at the android 
	 */
	public void send_output_soundsignal(String SoundID){
		if (controller_computer.network.send_sound("1;"+SoundID))
		{controller_computer.log.writelogfile("Sound signal was sent");}
		else{controller_computer.log.writelogfile("Sound signal wasn't sent");}
	}
}
