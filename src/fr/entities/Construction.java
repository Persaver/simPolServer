package fr.entities;

import java.io.Serializable;
import java.util.Arrays;

public class Construction extends AbstractEntity<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String designation;
	private String url;
	private int h;
	private int w;
	private Categorie categorie;

	public Construction() {
		super();

	}

	public Construction(String designation, int h, int w, String url, Categorie categorie) {
		this();
		this.designation = designation;
		this.h = h;
		this.w = w;
		this.url = url;
		this.categorie = categorie;
	}
	
	public Construction(Integer id, String designation, int h, int w, String url, Categorie categorie) {
		super(id);
		this.designation = designation;
		this.h = h;
		this.w = w;
		this.url = url;
		this.categorie = categorie;
	}

	public Construction(String designation, int h, int w, String url) {
		this();
		this.designation = designation;
		this.h = h;
		this.w = w;
		this.url = url;
	}

	public Construction(int id, String designation, int h, int w, String url) {
		super(id);
		this.designation = designation;
		this.h = h;
		this.w = w;
		this.url = url;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Construction [id=");
		builder.append(id);
		builder.append(", designation=");
		builder.append(designation);
		builder.append(", h=");
		builder.append(h);
		builder.append(", w=");
		builder.append(w);
		builder.append(", url=");
		builder.append(url);
		builder.append(", categorie=");
		builder.append(categorie);
		builder.append("]");
		return builder.toString();
	}

}
