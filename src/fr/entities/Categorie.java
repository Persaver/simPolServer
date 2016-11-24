package fr.entities;

import fr.interfaces.IEntity;

public class Categorie extends AbstractEntity<Categorie> implements IEntity{
	private static final long serialVersionUID = 1L;
	private String libelle;

	public Categorie() {
	}

	public Categorie(Integer id) {
		this(id, null);
	}
	public Categorie(Integer id, String libelle) {
		super(id);
		this.libelle = libelle;
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
