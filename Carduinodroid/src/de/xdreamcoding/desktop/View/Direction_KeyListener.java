package de.xdreamcoding.desktop.View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.xdreamcoding.desktop.Controller.Controller_Computer;

/**
* methods for working with KeyEvents and calculate direction/speed
* 
* @author Lars Vogel
* @version 12.06.2012
*/

public class Direction_KeyListener implements KeyListener{
	GUI_Computer gui_computer;
	Controller_Computer controller_computer;
	boolean up = false, left = false, right = false, down = false;
	
	public Direction_KeyListener(GUI_Computer GUI_computer, Controller_Computer Controller_computer){
		gui_computer = GUI_computer;
		controller_computer = Controller_computer;
	}
	
	// ***** KeyPressed Event ***************************************
	/** 
	 * When a key will be pressed, it is necessary to save the status
	 * in a separate variable. To work with a variable is easier then 
	 * with a keyCode or something like that.
	 */
	@Override
	public void keyPressed(KeyEvent e) {	
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {up=true;gui_computer.controller_Computer.car_controller.UpdateVariables(up,down,right,left);};
		if (key == KeyEvent.VK_RIGHT) {right=true;gui_computer.controller_Computer.car_controller.UpdateVariables(up,down,right,left);};
		if (key == KeyEvent.VK_LEFT) {left=true;gui_computer.controller_Computer.car_controller.UpdateVariables(up,down,right,left);};
		if (key == KeyEvent.VK_DOWN) {down=true;gui_computer.controller_Computer.car_controller.UpdateVariables(up,down,right,left);};
		if (key == KeyEvent.VK_SPACE) {controller_computer.sound_output.send_output_soundsignal("1");}
	}
	
	// ***** KeyRelease Event ***************************************
	/** 
	 *After a key is released, the variable will be set to false or sometimes
	 *an event will be started. The sliders will be controlled directly
	 *by certain methods to change their values. It should feel like a real
	 *gear.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {up=false;gui_computer.controller_Computer.car_controller.UpdateVariables(up, down, right, left);};
		if (key == KeyEvent.VK_RIGHT) {right=false;gui_computer.controller_Computer.car_controller.UpdateVariables(up, down, right, left);};
		if (key == KeyEvent.VK_LEFT) {left=false;gui_computer.controller_Computer.car_controller.UpdateVariables(up, down, right, left);};
		if (key == KeyEvent.VK_DOWN) {down=false;gui_computer.controller_Computer.car_controller.UpdateVariables(up, down, right, left);};
		if (key == KeyEvent.VK_1) {gui_computer.speed_slider.setValue(20);}
		if (key == KeyEvent.VK_2) {gui_computer.speed_slider.setValue(40);}
		if (key == KeyEvent.VK_3) {gui_computer.speed_slider.setValue(60);}
		if (key == KeyEvent.VK_4) {gui_computer.speed_slider.setValue(80);}
		if (key == KeyEvent.VK_5) {gui_computer.speed_slider.setValue(100);}
		if (key == KeyEvent.VK_W) {gui_computer.angle_slider.setValue(gui_computer.angle_slider.getValue()+10);}
		if (key == KeyEvent.VK_Q) {gui_computer.angle_slider.setValue(gui_computer.angle_slider.getValue()-10);}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}


