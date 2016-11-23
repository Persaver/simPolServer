package fr.gameEntities.batiments;

import fr.entities.BackupConstruction;
import fr.entities.Construction;
import fr.gameEntities.AbstractGameEntity;
import fr.gameEntities.indicateurs.Budget;
import fr.gameEntities.indicateurs.Population;
import fr.interfaces.IBatiment;

public abstract class Batiment extends AbstractGameEntity<BackupConstruction> implements IBatiment{
	// on cree une varible construction
	// => this.entity = BackupConstruction
	//    this.construction = Construction
	private Construction construction;

	public Batiment (){
		// on recupere la construction de l'entity backupConstruction
		this.construction = this.entity.getConstruction();
	}
	public Batiment(int baseS, int baseC, int baseR, int att){
		this();
		this.construction.setBaseSalarie(baseS);
		this.construction.setBaseCadre(baseC);
		this.construction.setModRisque(baseR);
		this.construction.setBaseAttractivite(att);
		this.prisePostes();
	}

	@Override
	public void ameliore(){
		this.entity.setNbSalarie(6);
	}

	@Override
	public int getPostePourvu(){
		return this.entity.getPostePourvu();
	}

	@Override
	public void prisePostes(){
		int pEmbauche = Population.nbIndiv(Budget.getAgeTravail(), Budget.getAgeRetraite())-getPostesPourvus();
		if (pEmbauche <= 0) {
			this.entity.setPostePourvu(0);
		} else{
			if (pEmbauche > (this.entity.getNbSalarie()+this.entity.getNbCadre())/10) {
				this.entity.setPostePourvu((this.entity.getNbSalarie()+this.entity.getNbCadre())/10);
			} else {
				this.entity.setPostePourvu(pEmbauche);
			}
		}
	}

	@Override
	public void ajoutPoste(){
		int pEmbauche = Population.nbIndiv(Budget.getAgeTravail(), Budget.getAgeRetraite())-getPostesPourvus();
		if (pEmbauche > (this.entity.getNbSalarie()+this.entity.getNbCadre())/10) {
			this.entity.setPostePourvu((this.entity.getNbSalarie()+this.entity.getNbCadre())/10);
		} else {
			this.entity.setPostePourvu(this.entity.getPostePourvu() + pEmbauche);
		}
	}
}
