package org.vitu.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.vitu.jdbc.model.Commune;

public class PlayWithCommune {

	public static void main(String[] args) {
		
		Map<String, Commune> communes = new HashMap<>();
		
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
				
				communes.put(codePostal, commune);
				
				line = reader.readLine();
			
			}
		
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
