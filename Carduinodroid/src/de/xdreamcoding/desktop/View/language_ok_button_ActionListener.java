package de.xdreamcoding.desktop.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JComboBox;
import javax.swing.JDialog;

/** Methods for working with the ok button of the language window.
*
* @author Benjamin L
* @version 14.06.2012.
*/
public class language_ok_button_ActionListener implements ActionListener{
	
	JDialog language_dialog;
	JComboBox language_ComboBox;
	String Selected;
	PrintWriter languagefile_writer;

	/**
	 * @param LANGUAGE_DIALOG		Dialog for language-selection.
	 * @param LANGUAGES				Available languages.
	 */
	public language_ok_button_ActionListener(JDialog LANGUAGE_DIALOG, JComboBox LANGUAGES){
		language_dialog = LANGUAGE_DIALOG;
		language_ComboBox = LANGUAGES;
	}
	
	/** 
	 * Closes the window where you can select the language and writes the selected language in language.txt when pressing the ok button in language window.
	 * After restarting CarDuinoDroid the language is changed. 
	 * 
	 * @param e			Event by pressing the ok button in language dialog.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		//visual options
		language_dialog.setVisible(false);
		language_dialog.setModal(false);
		
		//overwrite  language.txt
		Selected = (String)language_ComboBox.getSelectedItem();
		try{
			languagefile_writer = new PrintWriter(new FileWriter("src/View/language.txt"));
            languagefile_writer.print(Selected);
            languagefile_writer.flush();
            languagefile_writer.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        } 
	}
}
