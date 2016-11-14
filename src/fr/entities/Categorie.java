package fr.entities;

import java.io.Serializable;

public class Categorie implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String libelle;
	
	public Categorie() {
	}

	public Categorie(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [id=");
		builder.append(id);
		builder.append(", libelle=");
		builder.append(libelle);
		builder.append("]");
		return builder.toString();
	}
	
}
