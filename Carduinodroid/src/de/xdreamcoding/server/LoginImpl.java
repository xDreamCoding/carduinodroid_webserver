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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.xdreamcoding.client.Login;
import de.xdreamcoding.shared.User;

public class LoginImpl extends RemoteServiceServlet implements Login {

	@Override
	public String connectIP(String ip) throws IllegalArgumentException {
		return null;
	}

	@Override
	public User loginUser(String login, String password)  throws IllegalArgumentException {
		
		User user = new User();
		Connection connection = (Connection) getServletContext().getAttribute("connection");
		try {
			Statement stmt = connection.createStatement();
			if(stmt.execute("SELECT name FROM user WHERE login = '" + login + "' AND password = '" + password + "';")) {
				ResultSet rset = stmt.getResultSet();
				rset.next();
				user.setName(rset.getString(1));
			} else {
				stmt.close();
				throw new IllegalArgumentException("Login invalid.");
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
