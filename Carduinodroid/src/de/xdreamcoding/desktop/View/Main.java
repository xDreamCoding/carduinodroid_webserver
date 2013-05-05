package de.xdreamcoding.desktop.View;

/** This is the class which includes the main method.
* @author Benjamin L
* @version 13.06.2012.
*/
public class Main {

	/** 
	 * The main method is the method which runs after CarDuinoDroid was started. All other Methods will be opened directly or indirectly through main method.
	 * @param args			Elements referring to Strings which contains the arguments of command line which started the program.
	 */
	public static void main(String[] args) {
		GUI_Computer programwindow = new GUI_Computer();
		programwindow.setLocationRelativeTo(null);
		programwindow.setVisible(true);
		programwindow.setFocusable(true);
	}
}
