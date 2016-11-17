package fr.gameEntities.batiments;

import fr.entities.BackupConstruction;
import fr.entities.Construction;
import fr.gameEntities.AbstractGameEntity;
import fr.gameEntities.indicateurs.Budget;
import fr.gameEntities.indicateurs.Population;
import fr.interfaces.IBatiment;
import fr.interfaces.IEntity;

public abstract class Batiment<T> extends AbstractGameEntity<T> implements IBatiment{

	public Batiment (){
	}
	public Batiment(int baseS, int baseC, int baseR, int att){
		this();
		this.getEntity()
		((Construction) this.entity).setBaseSalarie(baseS);
		((Construction) this.entity).setBaseCadre(baseC);
		((Construction) this.entity).setModRisque(baseR);
		((Construction) this.entity).setBaseAttractivite(att);
		this.prisePostes();
	}
	
	public void ameliore(){
		((T) this.entity).setNbSalarie(6);
	}
	
	public int getPostePourvu(){
		return this.postePourvu;
	}
	public void prisePostes(){
		int pEmbauche = Population.nbIndiv(Budget.getAgeTravail(), Budget.getAgeRetraite())-getPostesPourvus();
		if (pEmbauche <= 0)
			this.postePourvu = 0;
		else{
			if (pEmbauche > (this.entity.getNbSalarie()+this.nbCadre)/10)
				this.postePourvu = (this.nbSalarie+this.nbCadre)/10;
			else
				this.postePourvu = pEmbauche;
		}
	}
	public void ajoutPoste(){
		int pEmbauche = Population.nbIndiv(Budget.getAgeTravail(), Budget.getAgeRetraite())-getPostesPourvus();
		if (pEmbauche > (this.nbSalarie+this.nbCadre)/10)
			this.postePourvu = (this.nbSalarie+this.nbCadre)/10;
		else
			this.postePourvu += pEmbauche;
	}
}
