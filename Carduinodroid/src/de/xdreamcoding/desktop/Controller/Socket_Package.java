package de.xdreamcoding.desktop.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import de.xdreamcoding.desktop.Model.Log;

/**
 * This class is used to receive Information from the Android-Application
 * @author Robin
 * @version 18.06.2012
 */
public class Socket_Package implements Runnable{
	
	private static final String ACKNOWLEDGE = "ACK";
	Socket socket_package;
	Network network;
	BufferedReader packagereader;
	BufferedWriter  packagewriter;
	InetSocketAddress port_package;
	Log log;
	
	
	
	Socket_Package(Network n_network)
	{
		socket_package = new Socket();
		network = n_network;
		//log = new Log();
		System.out.println(ACKNOWLEDGE);
	}

	/**
	 * Connect the Socket to the Android-Application
	 * @param nport_package The Socketaddress
	 */
	public void connect(InetSocketAddress nport_package)
	{
		port_package = nport_package;
		try {
			socket_package = new Socket();
			socket_package.connect(port_package);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/*
			 * noch überlegen
			 */
			System.out.println("fehler beim connecten");
		}
		
		try {
			packagereader = new BufferedReader(new InputStreamReader(socket_package.getInputStream()));
			packagewriter = new BufferedWriter(new OutputStreamWriter(socket_package.getOutputStream()));
		} catch (IOException e) {
			System.out.println("fehler beim inputstream");
		}
		System.out.println("ist connected");
	}
		

	/**
	 * Connect the Socket to the Android-Application
	 */
	public void connect(){
		try {
			socket_package.connect(port_package);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/*
			 * noch überlegen
			 */
			e.printStackTrace();
			}
			
			try {
				packagereader = new BufferedReader(new InputStreamReader(socket_package.getInputStream()));
				packagewriter = new BufferedWriter(new OutputStreamWriter(socket_package.getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		String message;
		long lastpackage = System.currentTimeMillis();
		while(!socket_package.isClosed())
		{
			try {
				if(packagereader.ready()){
				message = packagereader.readLine();
				System.out.println(message);
				//connection available
				if(message.equals(ACKNOWLEDGE))
				{
					System.out.println("ack gesendet");
					packagewriter.write(ACKNOWLEDGE);
					packagewriter.newLine();
					packagewriter.flush();
					System.out.println("fertig");
				}
				else
				{
				lastpackage = System.currentTimeMillis();
				network.receive_package(message);
				}
				}
					
					else
					{
						if(System.currentTimeMillis() > lastpackage + 10000) //10 sec
						{
							network.camera_picture.controller.log.writelogfile("Timeout. Please reconnect");
							network.close();
						}
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {
							System.out.println("fehler beim schlafen");
						}
					}
				} catch (IOException e) {
					System.out.println("fehler beim lesen");
				}
			}
		}
	
	public void close() {
		try {
			socket_package.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
