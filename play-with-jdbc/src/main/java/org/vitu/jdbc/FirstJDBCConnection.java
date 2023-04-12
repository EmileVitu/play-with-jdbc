package org.vitu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FirstJDBCConnection {

	public static void main(String... args) throws SQLException {
		
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db_jdbc",
				"jdbc-user",
				"user"
				);
		
		System.out.println("Connection = " + connection);
		
	}
	
}
