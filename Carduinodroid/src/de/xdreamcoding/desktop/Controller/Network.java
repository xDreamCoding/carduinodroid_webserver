/*
 * NOCH NICHT FERTIG BZW EPRÜFT MUSS NOCH ÜBERARBEITET WERDEN
 */

/*
 *ip wo hin?
 * 
 */
package de.xdreamcoding.desktop.Controller;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;


/**
 * This Class is responsible for the communication with the android-application
 * @author Robin
 * @version 18.06.2012
 */

public class Network {
	
	Socket_Picture socket_picture;
	Socket_Package socket_package;
	Socket_Controller socket_controller;
	String mobilephone_ip;
	Camera_Picture camera_picture;
	Packagedata packagedata;
	Thread t1;
	Thread t2;
	
	
	
	public Network(Packagedata n_packagedata, Camera_Picture n_camera_picture)
	{
		socket_picture = new Socket_Picture(this);
		socket_package = new Socket_Package(this);
		socket_controller = new Socket_Controller();
		camera_picture = n_camera_picture;
		packagedata = n_packagedata;
	}
	
//	public Network(String ip)
//	{
//		socket_picture = new Socket_Picture(this);
//		socket_package = new Socket_Package(this);
//		socket_controller = new Socket_Controller();
//		mobilephone_ip = ip;
//	}
	/*
	 * ports = ???????
	 */
	
	/**
	 * This method connects with the Android-Application
	 * @param ipstring
	 * @throws UnknownHostException 
	 */
	public void connect(String ipstring) throws UnknownHostException
	{
		InetAddress ip;
		mobilephone_ip = ipstring;
			ip = InetAddress.getByName(ipstring);
			InetSocketAddress port_controll = new InetSocketAddress(ip, 12345);
			InetSocketAddress port_package = new InetSocketAddress(ip, 12346);
			InetSocketAddress port_picture = new InetSocketAddress(ip, 12347);
			socket_picture.connect(port_picture);
			socket_controller.connect(port_controll);
			socket_package.connect(port_package);
			t1 = new Thread(socket_picture);
			t2 = new Thread(socket_package);
			t1.start();
			t2.start();

	}
	/*
	 * 1 = controllsignal
	 * 2 = settings
	 * 3 = sound
	 */
	
	
	/**
	 * @param direction
	 * @return
	 * @see Socket_Controller#send_controllsignal(String)
	 */
	public boolean send_controllsignal(String direction)
	{
		return socket_controller.send_controllsignal(direction);
	}
	
	
	/**
	 * @param settings
	 * @return
	 * @see Socket_Controller#send_camera_settings(String)
	 */
	public boolean send_camera_settings(String settings)
	{
		return socket_controller.send_camera_settings(settings);
	}
	
	/**
	 * @param sound_id
	 * @return
	 * @see Socket_Controller#send_sound(String)
	 */
	public boolean send_sound(String sound_id)
	{
		return socket_controller.send_sound(sound_id);
	}

	/**
	 * Transfer the message to the Packagedata
	 * @param message
	 * @see Packagedata#receive_package(String)
	 */
	public void receive_package(String message) {
		// TODO Auto-generated method stub
		packagedata.receive_package(message);
	}
	
	/**
	 * Transfer the image to the Camera_Picture
	 * @param bufferedImage
	 * @see Camera_Picture#receive_picture(ImageIcon)
	 */
	public void receive_picture(BufferedImage bufferedImage) {
		camera_picture.receive_picture(bufferedImage);
	}

	public void close() {
		socket_picture.close();
		socket_package.close();
		socket_controller.close();
	}
	
	
}
