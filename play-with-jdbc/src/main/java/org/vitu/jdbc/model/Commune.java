package org.vitu.jdbc.model;

public class Commune {

	private String codePostal;
	private String nom;

	public Commune(String codePostal, String nom) {
		this.codePostal = codePostal;
		this.nom = nom;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Commune [codePostal=" + codePostal + ", nom=" + nom + "]";
	}

}
