package de.xdreamcoding.desktop.View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSlider;

import de.xdreamcoding.desktop.Controller.Controller_Computer;

/** Class for changing the quality of pictures (1 to 100 is possible).
* 
* @author Benjamin L
* @author Lars
* @version 27.06.2012.
*/
public class Quality_MouseListener implements MouseListener{
	JSlider quality_slider;
	Controller_Computer controller_computer;
	
	/**
	 * 
	 * @param QUALITY_SLIDER	Slider which changes quality.
	 * @param LOG				Log which is used.
	 */
	public Quality_MouseListener(JSlider QUALITY_SLIDER, Controller_Computer Controller_computer){
		quality_slider = QUALITY_SLIDER;
		controller_computer = Controller_computer;
	}

	// necessary using MouseListener
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	// necessary using MouseListener
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	// necessary using MouseListener
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	// necessary using MouseListener
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * An event which is happen after someone changed the value of quality slider.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
//		controller_computer.gui_computer.log.writelogfile("Sent: Change quality to " + String.valueOf(quality_slider.getValue()) + ".");
		controller_computer.camera_settings.send_change_quality(String.valueOf(quality_slider.getValue()));
	}
}

