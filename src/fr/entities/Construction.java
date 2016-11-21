package fr.entities;

import java.io.Serializable;
import java.util.Map;

public class Construction extends AbstractEntity<Integer>{

	private static final long serialVersionUID = 1L;
	private String designation;
	private String url;
	private int h;
	private int w;
	private int baseSalarie;
	private int baseCadre;
	private int baseRisque = 0;
	private int baseAttractivite;
	private int modSalarie;
	private int modCadre;
	private int modRisque;
	private int modAttractivite;
	private Map<String, Integer> specificite;
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

	public int getBaseSalarie() {
		return baseSalarie;
	}

	public void setBaseSalarie(int baseSalarie) {
		this.baseSalarie = baseSalarie;
	}

	public int getBaseCadre() {
		return baseCadre;
	}

	public void setBaseCadre(int baseCadre) {
		this.baseCadre = baseCadre;
	}

	public int getBaseRisque() {
		return baseRisque;
	}

	public void setBaseRisque(int baseRisque) {
		this.baseRisque = baseRisque;
	}

	public int getBaseAttractivite() {
		return baseAttractivite;
	}

	public void setBaseAttractivite(int baseAttractivite) {
		this.baseAttractivite = baseAttractivite;
	}

	public int getModSalarie() {
		return modSalarie;
	}

	public void setModSalarie(int modSalarie) {
		this.modSalarie = modSalarie;
	}

	public int getModCadre() {
		return modCadre;
	}

	public void setModCadre(int modCadre) {
		this.modCadre = modCadre;
	}

	public int getModRisque() {
		return modRisque;
	}

	public void setModRisque(int modRisque) {
		this.modRisque = modRisque;
	}

	public int getModAttractivite() {
		return modAttractivite;
	}

	public void setModAttractivite(int modAttractivite) {
		this.modAttractivite = modAttractivite;
	}

	public Map<String, Integer> getSpecificite() {
		return specificite;
	}

	public void setSpecificite(Map<String, Integer> specificite) {
		this.specificite = specificite;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
