package fr.entities;

import java.io.Serializable;

public class Categorie extends AbstractEntity<Categorie> implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String libelle;

	public Categorie() {
	}

	public Categorie(Integer id) {
		super(id);
	}
	public Categorie(Integer id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [id=");
		builder.append(this.id);
		builder.append(", libelle=");
		builder.append(this.libelle);
		builder.append("]");
		return builder.toString();
	}

}
