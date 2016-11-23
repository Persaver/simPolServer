package fr.entities;

public class Construction extends AbstractEntity<Construction>{

	private static final long serialVersionUID = 1L;
	private String designation;
	private String url;
	private int h;
	private int w;
	private int baseSalarie;
	private int baseCadre;
	private int baseRisque;
	private int baseAttractivite;
	private int modSalarie;
	private int modCadre;
	private int modRisque;
	private int modAttractivite;
	private String specificite;
	private Categorie categorie;

	public Construction() {
		super();
	}

	public Construction(Integer id) {
		super(id);
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

	public Construction(Integer id, String designation, String url, int h, int w, int baseSalarie, int baseCadre, int baseRisque,
			int baseAttractivite, int modSalarie, int modCadre, int modRisque, int modAttractivite, String specificite) {
		super(id);
		this.designation = designation;
		this.url = url;
		this.h = h;
		this.w = w;
		this.baseSalarie = baseSalarie;
		this.baseCadre = baseCadre;
		this.baseRisque = baseRisque;
		this.baseAttractivite = baseAttractivite;
		this.modSalarie = modSalarie;
		this.modCadre = modCadre;
		this.modRisque = modRisque;
		this.modAttractivite = modAttractivite;
		this.specificite = specificite;
	}

	public Construction(Integer id, String designation, String url, int h, int w, int baseSalarie, int baseCadre, int baseRisque,
			int baseAttractivite, int modSalarie, int modCadre, int modRisque, int modAttractivite, String specificite,
			Categorie categorie) {
		super(id);
		this.designation = designation;
		this.url = url;
		this.h = h;
		this.w = w;
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

	public int getH() {
		return this.h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getW() {
		return this.w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getBaseSalarie() {
		return this.baseSalarie;
	}

	public void setBaseSalarie(int baseSalarie) {
		this.baseSalarie = baseSalarie;
	}

	public int getBaseCadre() {
		return this.baseCadre;
	}

	public void setBaseCadre(int baseCadre) {
		this.baseCadre = baseCadre;
	}

	public int getBaseRisque() {
		return this.baseRisque;
	}

	public void setBaseRisque(int baseRisque) {
		this.baseRisque = baseRisque;
	}

	public int getBaseAttractivite() {
		return this.baseAttractivite;
	}

	public void setBaseAttractivite(int baseAttractivite) {
		this.baseAttractivite = baseAttractivite;
	}

	public int getModSalarie() {
		return this.modSalarie;
	}

	public void setModSalarie(int modSalarie) {
		this.modSalarie = modSalarie;
	}

	public int getModCadre() {
		return this.modCadre;
	}

	public void setModCadre(int modCadre) {
		this.modCadre = modCadre;
	}

	public int getModRisque() {
		return this.modRisque;
	}

	public void setModRisque(int modRisque) {
		this.modRisque = modRisque;
	}

	public int getModAttractivite() {
		return this.modAttractivite;
	}

	public void setModAttractivite(int modAttractivite) {
		this.modAttractivite = modAttractivite;
	}

	public String getSpecificite() {
		return this.specificite;
	}

	public void setSpecificite(String specificite) {
		this.specificite = specificite;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Construction [id=");
		builder.append(this.id);
		builder.append(", designation=");
		builder.append(this.designation);
		builder.append(", h=");
		builder.append(this.h);
		builder.append(", w=");
		builder.append(this.w);
		builder.append(", url=");
		builder.append(this.url);
		builder.append(", categorie=");
		builder.append(this.categorie);
		builder.append("]");
		return builder.toString();
	}

}
