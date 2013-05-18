package de.xdreamcoding.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import de.xdreamcoding.desktop.Controller.Controller_Computer;
import de.xdreamcoding.desktop.Model.GPSTrack;
import de.xdreamcoding.desktop.Model.Log;
import de.xdreamcoding.utilities.*;
import de.xdreamcoding.utilities.Config.Options;

public class MyServletContextListener implements ServletContextListener {

	private ServletContext context;
	private Log log;

	  /*This method is invoked when the Web Application has been removed 
	  and is no longer able to accept requests
	  */
	
	  public void contextDestroyed(ServletContextEvent event)
	  {
	    log.writelogfile("contextDestroyed.");
	    this.context = null;

	  }


	  //This method is invoked when the Web Application
	  //is ready to service requests

	  public void contextInitialized(ServletContextEvent event)
	  {
		ServletContext context = event.getServletContext();
	    log = new Log();
	    context.setAttribute("log", log);
	    log.writelogfile("contextInitialized. Log instanciated.");
	    
	    GPSTrack gps = new GPSTrack();
	    log.writelogfile("GPSTracker instanciated.");
	    
	    Controller_Computer controller = new Controller_Computer(log, gps);
	    context.setAttribute("controller", controller);
	    log.writelogfile("Controller_Computer instanciated.");
	    
	    Config config = new Config(log);
	    config.readOptions();
	    //context.setAttribute("config", config);
	    
	    Options options = config.getOptions();
	    // check if any options were found, if not load default
	    if(options.dbAddress == null) { 	    	
	    	/*
	    	 * DB läuft auf/im 
	    	 * 	localhost 	-> true
	    	 * 	FEM-Netz	-> false
	    	 */
	    	boolean localhost = false;	
	    	options.fahrZeit = 10;
	    	if(localhost) {
	    		options.dbAddress = "localhost:3306/carduinodroid";
	    		options.dbUser = "root";
	    		options.dbPW = "test";
	    	} else {
	    		options.dbAddress = "sehraf-pi:3306/carduinodroid";
	    		options.dbUser = "test";
	    		options.dbPW = "test";
	    	}
	    	config.writeOptions(options);
	    }	    
	    context.setAttribute("options", options);
	    log.writelogfile("Options loaded");
	    
//	    try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carduinodroid", "root", "test");
//			log.writelogfile("DB Connection established.");
//			context.setAttribute("connection", connection);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
	    DBConnector db = new DBConnector(log, options);
	    if(db.connect()) {
	    	Connection dbConnection = db.getDbConnection();
	    	context.setAttribute("connection", dbConnection);
	    }
	  }

}
