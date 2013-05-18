package de.xdreamcoding.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import de.xdreamcoding.desktop.Model.Log;
import de.xdreamcoding.utilities.Config.Options;

public class DBConnector {
	Connection dbConnection;
	Log log;
	Options options;
	
	public DBConnector(Log l, Options opt) {
		log = l;
		options = opt;
	}
	
	public boolean connect() {
		try {
			if(dbConnection == null || dbConnection.isClosed()) {			
				Class.forName("org.mariadb.jdbc.Driver");
				dbConnection = DriverManager.getConnection("jdbc:mysql://" + options.dbAddress, options.dbUser, options.dbPW);
				log.writelogfile("DB Connection established.");
			} 
			else
				log.writelogfile("DB Connection already established.");
		}
		catch (Exception e) {
			log.writelogfile("DB Connection failed.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @return the dbConnection
	 */
	public Connection getDbConnection() {
		return dbConnection;
	}
}
