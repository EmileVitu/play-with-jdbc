package org.vitu.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.vitu.jdbc.model.Commune;

public class PlayWithCommune {

	public static void main(String[] args) throws SQLException {
		
		Map<String, Commune> communes = new HashMap<>();
		
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db_jdbc",
				"jdbc-user",
				"user"
				);
		
		String sql = "insert into commune(code_postal, nom) values (?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		Path path = Path.of("data/maires-25-04-2014.csv");
		try(BufferedReader reader = Files.newBufferedReader(path);) {
			
			String line = reader.readLine();
			line = reader.readLine();
			
			while (line != null) {
				
				String[] split = line.split(";");
				
				String codeDepartement = split[0];
				if (codeDepartement.length() == 1) {
					codeDepartement = "0" + codeDepartement;
				}
				
				String codeInsee = split[2];
				if (codeInsee.length() == 1) {
					codeInsee = "00" + codeInsee;
				} else if (codeInsee.length() == 2) {
					codeInsee = "0" + codeInsee;
				}
				
				String codePostal = codeDepartement + codeInsee;
				String nom = split[3];
				
				Commune commune = new Commune(codePostal, nom);
				
				Commune previousCommune = communes.put(codePostal, commune);
				if (previousCommune != null) {
					System.out.println("Doublon = " + previousCommune);
				} else {
					preparedStatement.setString(1, codePostal);
					preparedStatement.setString(2, nom);
					preparedStatement.addBatch();
				}
								
				line = reader.readLine();
			
			}
			System.out.println("Executing barch");
			int[] counts = preparedStatement.executeBatch();
			System.out.println("Done batch");
			
			int count = Arrays.stream(counts).sum();
			System.out.println("Nombre de communes créées = " + count);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
