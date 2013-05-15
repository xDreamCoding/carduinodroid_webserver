/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package de.xdreamcoding.server;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.xdreamcoding.client.Login;
import de.xdreamcoding.desktop.Controller.Controller_Computer;
import de.xdreamcoding.desktop.Model.Log;
import de.xdreamcoding.shared.FieldVerifier;
import de.xdreamcoding.shared.User;

public class LoginImpl extends RemoteServiceServlet implements Login {

	@Override
	public User loginUser(String login, String password)  throws IllegalArgumentException {
		
		Log log = (Log) getServletContext().getAttribute("log");
		
		User user = new User();
		Connection connection = (Connection) getServletContext().getAttribute("connection");
		
		try {
			Statement stmt = connection.createStatement();
			stmt.execute("SELECT name FROM user WHERE login = '" + escapeHtml(login) + "' AND password = '" + escapeHtml(password) + "';");
			ResultSet rset = stmt.getResultSet();
			if(!rset.isBeforeFirst()) {
				throw new IllegalArgumentException("Login invalid.");
			} else {
				rset.next();
				user.setName(rset.getString(1));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Database Error.");
		}
		return user;
	}
	
	@Override
	public String connectIP(String input) throws IllegalArgumentException {
		//Get Objects through ServletContext.
		Log log = (Log) getServletContext().getAttribute("log");
		Controller_Computer controller = (Controller_Computer) getServletContext().getAttribute("controller");
		
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidIP(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Please enter a valid IP.");
		}


		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		log.writelogfile("IP received " + input + " trying to connect.");
		String stringReturn = "IP was " + input + ". ";
		
		boolean error = false;
		try {
			controller.network.connect(input);
		} catch (UnknownHostException e) {
			error = true;
			e.printStackTrace();
		}
		if(error) {
			log.writelogfile("Connection failure.");
			throw new IllegalArgumentException(stringReturn + "Connection failure.");
		} else {
			log.writelogfile("Connection established.");
			stringReturn += "Connection seems to be established.";
		}
		return stringReturn;
	}
	
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
