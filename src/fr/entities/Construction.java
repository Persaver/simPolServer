package fr.entities;

import java.util.Map;

public class Construction extends AbstractEntity<Construction> {

	private static final long serialVersionUID = 1L;
	private String designation;
	private Integer h;
	private Integer w;
	private Integer price;
	private Integer baseSalarie;
	private Integer baseCadre;
	private Integer baseRisque;
	private Integer baseAttractivite;
	private Integer modSalarie;
	private Integer modCadre;
	private Integer modRisque;
	private Integer modAttractivite;
	private Map<String,Integer> specificite;
	private Categorie categorie;

	public Construction() {
	}

	public Construction(Integer id){
		this(id, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	public Construction(Integer id, String designation, Integer h, Integer w, Integer price, Integer baseSalarie, Integer baseCadre,
			Integer baseRisque, Integer baseAttractivite, Integer modSalarie, Integer modCadre, Integer modRisque,
			Integer modAttractivite, Map<String,Integer> specificite, Categorie categorie) {
		super(id);
		this.designation = designation;
		this.h = h;
		this.w = w;
		this.price = price;
		this.baseSalarie = baseSalarie;
		this.baseCadre = baseCadre;
		this.baseRisque = baseRisque;
		this.baseAttractivite = baseAttractivite;
		this.modSalarie = modSalarie;
		this.modCadre = modCadre;
		this.modRisque = modRisque;
		this.modAttractivite = modAttractivite;
		this.specificite = specificite;
		this.categorie = categorie;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Integer getH() {
		return this.h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public Integer getW() {
		return this.w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getBaseSalarie() {
		return this.baseSalarie;
	}

	public void setBaseSalarie(Integer baseSalarie) {
		this.baseSalarie = baseSalarie;
	}

	public Integer getBaseCadre() {
		return this.baseCadre;
	}

	public void setBaseCadre(Integer baseCadre) {
		this.baseCadre = baseCadre;
	}

	public Integer getBaseRisque() {
		return this.baseRisque;
	}

	public void setBaseRisque(Integer baseRisque) {
		this.baseRisque = baseRisque;
	}

	public Integer getBaseAttractivite() {
		return this.baseAttractivite;
	}

	public void setBaseAttractivite(Integer baseAttractivite) {
		this.baseAttractivite = baseAttractivite;
	}

	public Integer getModSalarie() {
		return this.modSalarie;
	}

	public void setModSalarie(Integer modSalarie) {
		this.modSalarie = modSalarie;
	}

	public Integer getModCadre() {
		return this.modCadre;
	}

	public void setModCadre(Integer modCadre) {
		this.modCadre = modCadre;
	}

	public Integer getModRisque() {
		return this.modRisque;
	}

	public void setModRisque(Integer modRisque) {
		this.modRisque = modRisque;
	}

	public Integer getModAttractivite() {
		return this.modAttractivite;
	}

	public void setModAttractivite(Integer modAttractivite) {
		this.modAttractivite = modAttractivite;
	}

	public Map<String,Integer> getSpecificite() {
		return this.specificite;
	}

	public void setSpecificite(Map<String,Integer> specificite) {
		this.specificite = specificite;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Construction [designation=");
		builder.append(this.designation);
		builder.append(", h=");
		builder.append(this.h);
		builder.append(", w=");
		builder.append(this.w);
		builder.append(", price=");
		builder.append(this.price);
		builder.append(", baseSalarie=");
		builder.append(this.baseSalarie);
		builder.append(", baseCadre=");
		builder.append(this.baseCadre);
		builder.append(", baseRisque=");
		builder.append(this.baseRisque);
		builder.append(", baseAttractivite=");
		builder.append(this.baseAttractivite);
		builder.append(", modSalarie=");
		builder.append(this.modSalarie);
		builder.append(", modCadre=");
		builder.append(this.modCadre);
		builder.append(", modRisque=");
		builder.append(this.modRisque);
		builder.append(", modAttractivite=");
		builder.append(this.modAttractivite);
		builder.append(", specificite=");
		builder.append(this.specificite);
		builder.append(", categorie=");
		builder.append(this.categorie);
		builder.append(", id=");
		builder.append(this.id);
		builder.append("]");
		return builder.toString();
	}

}
