package de.xdreamcoding.desktop.Controller;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


/**
 * This class is used to update the Camera pictures on the GUI
 * @author Robin
 * 
 */
public class Camera_Picture {

	Controller_Computer controller;
	
	public Camera_Picture(Controller_Computer controller) {
		this.controller = controller;
	}
	
	/**
	 * This method updates the Image
	 * @param bufferedImage
	 */
	public void receive_picture(BufferedImage bufferedImage) {
		BufferedImage image = bufferedImage;
		image = rotate(image, Math.toRadians(90));
//		controller.gui_computer.image.setIcon(new ImageIcon(image));
	}
	
	public BufferedImage rotate(BufferedImage image, double angle) {
	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	    int w = image.getWidth(), h = image.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
	    GraphicsConfiguration gc = getDefaultConfiguration();
	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.translate((neww-w)/2, (newh-h)/2);
	    g.rotate(angle, w/2, h/2);
	    g.drawRenderedImage(image, null);
	    g.dispose();
	    return result;
	}
	
	   public static GraphicsConfiguration getDefaultConfiguration() {
		   GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		   GraphicsDevice gd = ge.getDefaultScreenDevice();
		   return gd.getDefaultConfiguration();
		   }


}
