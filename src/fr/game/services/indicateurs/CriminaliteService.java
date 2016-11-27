package fr.game.services.indicateurs;

import fr.Dao.CriminaliteDAO;
import fr.entities.Criminalite;
import fr.game.services.gameControllers.AbstractGameEntity;

public class CriminaliteService extends AbstractGameEntity<Criminalite ,CriminaliteDAO> {
	private Criminalite entity;

	public CriminaliteService(Criminalite crime, CriminaliteDAO criminaliteDAO){
		super(crime,criminaliteDAO);
		this.entity = this.getEntity();
	}
	// Une fonction pour calculer la valeur quotidienne du crime mineur
	public void actuIndicMin(PopulationService p, BudgetService b){
		this.entity.setIndicMineur((int)((9*b.pauvrete(p)/*+insatisfaction(p)*/)*15*Math.sqrt(p.nbIndiv(0, 129))/3000));
	}
	public void cumulMineur(int modif){
		this.entity.setCrimeMineur(this.entity.getCrimeMineur() + modif);
		if (this.entity.getCrimeMineur() < 0) {
			this.entity.setCrimeMineur(0);
		}
	}
	// Une fonction pour calculer la valeur quotidienne du crime moyen
	public void actuIndicMo(PopulationService p, BudgetService b){
		this.entity.setIndicMineur((int)((6.5*b.pauvrete(p)/*+3.5*insatisfaction(p)*/)*10*Math.sqrt(p.nbIndiv(0, 129))/3000));
	}
	public void cumulMoyen(int modif){
		this.entity.setCrimeMoyen(this.entity.getCrimeMoyen() + modif);
		if (this.entity.getCrimeMoyen() < 0) {
			this.entity.setCrimeMoyen(0);
		}
	}
	// Une fonction pour calculer la valeur quotidienne du crime grave
	public void actuIndicG(PopulationService p, BudgetService b){
		this.entity.setIndicGrave((int)((3.5*b.pauvrete(p)/*+6.5*insatisfaction(p)*/)*5*Math.sqrt(p.nbIndiv(0, 129))/3000));
	}
	public void cumulGrave(int modif){
		this.entity.setCrimeGrave(this.entity.getCrimeGrave() + modif);
		if (this.entity.getCrimeGrave() < 0) {
			this.entity.setCrimeGrave(0);
		}
	}
	// Une fonction pour calculer la valeur quotidienne du terrorisme
	public void actuIndicT(PopulationService p, BudgetService b){
		this.entity.setIndicTerrorisme((int)((b.pauvrete(p)/*+9*insatisfaction(p)*/)*Math.sqrt(p.nbIndiv(0, 129))/3000));
	}
	public void cumulTerreur(int modif){
		this.entity.setCrimeTerroriste(this.entity.getCrimeTerroriste() + modif);
		if (this.entity.getCrimeTerroriste() < 0) {
			this.entity.setCrimeTerroriste(0);
		}
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}