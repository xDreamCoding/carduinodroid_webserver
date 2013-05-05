package de.xdreamcoding.server;

import java.net.UnknownHostException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.xdreamcoding.client.GreetingService;
import de.xdreamcoding.desktop.Controller.Controller_Computer;
import de.xdreamcoding.desktop.Model.Log;
import de.xdreamcoding.shared.FieldVerifier;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		//Get Objects through ServletContext.
		Log log = (Log) getServletContext().getAttribute("log");
		Controller_Computer controller = (Controller_Computer) getServletContext().getAttribute("controller");
		
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidIP(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Please enter a valid IP.");
		}

//		String serverInfo = getServletContext().getServerInfo();
//		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		log.writelogfile("IP received " + input + " trying to connect.");
//		userAgent = escapeHtml(userAgent);
		String stringReturn = "IP was " + input + ". ";
				
		try {
			controller.network.connect(input);
		} catch (UnknownHostException e) {
			log.writelogfile("Connection failure.");
			stringReturn += "Connection failure.";
			e.printStackTrace();
		}
		log.writelogfile("Connection established.");
		stringReturn += "Connection seems to be established.";
		return stringReturn;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
