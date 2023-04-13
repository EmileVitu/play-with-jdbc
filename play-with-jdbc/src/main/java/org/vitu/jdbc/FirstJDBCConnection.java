package org.vitu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstJDBCConnection {

	public static void main(String... args) {
		
		try(Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db_jdbc",
				"jdbc-user",
				"user"
				);){
		
			System.out.println("Connection = " + connection);
			
			String sql = "select count(*) count from user";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			System.out.println("\nSQL = " + sql);
			while(resultSet.next()) {
				long count = resultSet.getLong(1);
				System.out.println("Count = " + count);
			}
			
			String sql2 = "select name from user";
			resultSet = statement.executeQuery(sql2);
			
			System.out.println("\nSQL = " + sql2);
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				System.out.println("Name = " + name);
			}
			
			String sql3 = "select id, name from user";
			resultSet = statement.executeQuery(sql3);
			
			System.out.println("\nSQL = " + sql3);
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("Id, Name = " + id + ", " + name);
			}
			
			String sql4 = "select id, name from user where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql4);
			preparedStatement.setInt(1, 4);
			resultSet = preparedStatement.executeQuery();
			
			System.out.println("\nSQL = " + sql4);
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("Id, Name = " + id + ", " + name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
















