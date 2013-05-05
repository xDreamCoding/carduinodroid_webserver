package de.xdreamcoding.desktop.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import de.xdreamcoding.desktop.Model.Log;

/** Methods for working with connect menuitem.
*
* @author Benjamin L
* @version 11.06.2012.
*/
public class Connect_ActionListener implements ActionListener{
	
	GUI_Computer gui_computer;
	String Question;
	String Ip_adress;
	Log log;
	JLabel ip;
	
	/** 
	 * @param QUESTION		Questiontext by opening the connectwindow. 
	 * @param LOG			Log which is used.
	 * @param IP			Label which should show the actual connected IP.
	 * @param gui_Computer	The GUI which calls this constructer. 
	 */
	public Connect_ActionListener(String QUESTION, Log LOG, JLabel IP, GUI_Computer gui_Computer){
		Question = QUESTION;
		log = LOG;
		ip = IP;
		gui_computer = gui_Computer;
	}

	/** Opens a new window to enter the ip of the phone when pressing the connect menutitem.
	 * 
	 * @param e			Event by pressing the connect menuitem.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ((Ip_adress = JOptionPane.showInputDialog(Question)).length() >= 7){
			ip.setText(Ip_adress);
			log.writelogfile("Connect to: " + Ip_adress);
//			gui_computer.controller_Computer.network.connect(Ip_adress);
		}
	}
}
