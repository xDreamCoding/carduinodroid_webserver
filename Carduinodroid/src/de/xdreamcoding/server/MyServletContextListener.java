package de.xdreamcoding.server;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import de.xdreamcoding.desktop.Controller.Controller_Computer;
import de.xdreamcoding.desktop.Model.GPSTrack;
import de.xdreamcoding.desktop.Model.Log;

public class MyServletContextListener implements ServletContextListener {

	private ServletContext context;
	private Controller_Computer controller;
	private Log log;
	private GPSTrack gps;

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
	    context = event.getServletContext();
	    log = new Log();
	    context.setAttribute("log", log);
	    log.writelogfile("contextInitialized. Log instanciated.");
	    
	    gps = new GPSTrack();
	    log.writelogfile("GPSTracker instanciated.");
	    
	    controller = new Controller_Computer(log, gps);
	    context.setAttribute("controller", controller);
	    log.writelogfile("Controller_Computer instanciated.");
	    
	  }

}
