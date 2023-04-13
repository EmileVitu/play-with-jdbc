package org.vitu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstJDBCConnection {

	public static void main(String... args) throws SQLException {
		
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db_jdbc",
				"jdbc-user",
				"user"
				);
		
		System.out.println("Connection = " + connection);
		
		String sql = "select count(*) count from user";
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			long count = resultSet.getLong(1);
			System.out.println("Count = " + count);
		}
	}
}