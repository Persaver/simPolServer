package fr.game.services.indicateurs;

import java.util.List;

import fr.Dao.SanteDAO;
import fr.entities.Sante;
import fr.game.services.constructions.AbstractConstructionService;
import fr.game.services.gameControllers.AbstractGameEntity;

public class SanteService extends AbstractGameEntity<Sante, SanteDAO> {

	public SanteService(Sante entity, SanteDAO entityDao) {
		super(entity, entityDao);
	}

	public void journeeMedicale(PopulationService p, BudgetService b){
		this.tombeMalades(p);		// On comptabilise les nouveaux malades
		// a revoir avec liste
		System.out.println(this.entity.getNbMalades() + " gens sont malades");
		//this.accidente(p, b);			// de meme pour les accidentes
		System.out.println(this.entity.getNbAccidents() + " gens ont eu un accident");
		this.mortalite(p, b);			// Parmi ces victimes, certaines ne se reveilleront jamais
		System.out.println(this.entity.getNbMalades()+this.entity.getNbAccidents() + " ne sont pas encore morts");
		this.recupSoins();			// Heureusement, les medecins sont la avec leurs competences
		System.out.println(this.entity.getSoins() + " patients vont �tre secourus");
		this.apportMedicaux();		// Ces chevaliers de la sante sauvent autant de vies que possible
		System.out.println("il reste " + this.entity.getNbMalades() + " malades et " + this.entity.getNbAccidents() + " accidentes");
		this.guerison();				// Et puis, il y a ceux qui se soignent en mangeant bio
		System.out.println(this.entity.getNbMalades() + this.entity.getNbAccidents() + " patients sont encore dans les hopitaux en fin de soiree");
	}

	public void tombeMalades (PopulationService p){
		int nbSains =  p.nbIndiv()- this.entity.getNbMalades();
		this.entity.setNbMalades(this.entity.getNbMalades() + (int)((Math.random()*(100-this.entity.getHygiene())*nbSains)/3000));	// actualisation quotidienne du nombre de malades
	}
	public void accidente (PopulationService p, BudgetService b,List <AbstractConstructionService> liste){
		int nbSaufs = p.nbIndiv(0, b.getAgeRetraite())- this.entity.getNbAccidents();	// Seuls ceux qui se deplacent et ne sont pas deja accidente peuvent subir un accident
		// a revoir modif avec list
		//this.entity.setNbAccidents(this.entity.getNbAccidents() + (int)((Math.random()*Batiment.getTotalRisque()*nbSaufs)/1000/30));
	}
	public void ajoutBlesse(int nb){			// Victimes d'agressions
		this.entity.setNbAccidents(this.entity.getNbAccidents() + nb);
	}
	public void mortalite (PopulationService p, BudgetService b){
		int mort = (int)(Math.random()*this.entity.getEchecs()*this.entity.getNbAccidents())/100;		// Tous les accidentes ne survivent pas.
		this.entity.setNbAccidents(this.entity.getNbAccidents() - mort);
		p.retraitPopulation(mort, 0, b.getAgeRetraite());
		mort = (int)(Math.random()*this.entity.getEchecs()*this.entity.getNbMalades())/100;			// Les malades non plus
		p.retraitPopulation(mort, 0, 129);
		this.entity.setNbMalades(this.entity.getNbMalades() - mort);
	}
	public void recupSoins (){
		//		soins = Hopital.soinsTotal();
	}

	public void apportMedicaux(){
		int patients = this.entity.getNbAccidents()+this.entity.getNbMalades();
		int ratio = (this.entity.getNbMalades()*100)/patients;		// reparti les efforts medicaux
		if (((this.entity.getSoins()*ratio)/100) > this.entity.getNbMalades()){
			this.entity.setSoins(this.entity.getSoins() -this.entity.getNbMalades());
			this.entity.setNbMalades(0);
		} else {
			this.entity.setNbMalades(this.entity.getNbMalades() - ((this.entity.getSoins()*ratio)/100));
			this.entity.setSoins((this.entity.getSoins()*ratio)/100);
		}										// L'aide medicale pour les malades a deja ete utilisee, tout le reste est dedie aux accidents
		if (this.entity.getSoins() > this.entity.getNbAccidents()) {
			this.entity.setNbAccidents(0);
		} else {
			this.entity.setNbAccidents(this.entity.getNbAccidents() - this.entity.getSoins());
		}
		this.entity.setSoins(0);								// l'aide medicale qui n'a pas ete utilisee est perdue
	}

	public void guerison(){
		this.entity.setNbMalades(this.entity.getNbMalades() - (int)((Math.random()*this.entity.getHygiene()*this.entity.getNbMalades())/1000));		// parmi les malades entre 0 et 10% guerissent tout seuls en fonctio de l'hygiene
		this.entity.setNbAccidents(this.entity.getNbAccidents() - (int)((Math.random()*this.entity.getHygiene()*this.entity.getNbAccidents())/1000));	// idem pour les maladroits
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
