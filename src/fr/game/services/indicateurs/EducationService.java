package fr.game.services.indicateurs;

import fr.Dao.EducationDAO;
import fr.entities.Budget;
import fr.entities.Education;
import fr.game.services.gameControllers.AbstractGameEntity;

public class EducationService extends AbstractGameEntity<Education, EducationDAO> {

	public EducationService(Education entity, EducationDAO entityDao) {
		super(entity, entityDao);
	}

	public void recupEducation(PopulationService p, Budget b){
		int place = this.entity.getEdTotale();
		int etudiant = p.nbIndiv(0, b.getAgeTravail());
		if (place-etudiant < 0) {
			this.entity.setEdTotale((place*500/etudiant)*((int)(Math.sqrt(b.getAgeTravail())/4)));		// varie aussi selon l'age estime de fin d'etude. a 16 ans, facteur de 1, ï¿½ 25 ans, facteur de 1.25, 36 ans, facteur de 1.5 ...
		} else {						// S'il y a plus de place, alors, les cours du soir et les formation d'ouvrier peuvent augmenter la productivite
			this.entity.setEdTotale(((100+(place-etudiant)/4)*5)*((int)(Math.sqrt(b.getAgeTravail())/4)));
		}
	}

	public void distibution (){
		this.entity.setEdSecurite(this.entity.getEdTotale()%5 > 0 ? this.entity.getEdTotale()/5+1 : this.entity.getEdTotale()/5);
		this.entity.setEdEntretien(this.entity.getEdTotale()%5 > 1 ? this.entity.getEdTotale()/5+1 : this.entity.getEdTotale()/5);
		this.entity.setEdSante(this.entity.getEdTotale()%5 > 2 ? this.entity.getEdTotale()/5+1 : this.entity.getEdTotale()/5);
		this.entity.setEdRecherche(this.entity.getEdTotale()%5 > 3 ? this.entity.getEdTotale()/5+1 : this.entity.getEdTotale()/5);
		this.entity.setEdTourisme(this.entity.getEdTotale()%5 > 4 ? this.entity.getEdTotale()/5+1 : this.entity.getEdTotale()/5);
	}

	public void monterSecurite (int mod){
		int possible = 4;					// S'il n'est plus possible de prendre des points d'education dans les autres secteurs, on arrete
		while (mod > 0 && possible > 0){
			if (this.entity.getEdEntretien() > 0 && mod > 0){		// On verifie pour chaque domaine si on peut prendre des points d'education
				this.entity.setEdEntretien(this.entity.getEdEntretien()-1);
				this.entity.setEdSecurite(this.entity.getEdSecurite()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdSante() > 0 && mod > 0){
				this.entity.setEdSante(this.entity.getEdSante()-1);
				this.entity.setEdSecurite(this.entity.getEdSecurite()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdRecherche() > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				this.entity.setEdSecurite(this.entity.getEdSecurite()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdTourisme() > 0 && mod > 0){
				this.entity.setEdTourisme(this.entity.getEdTourisme()-1);
				this.entity.setEdSecurite(this.entity.getEdSecurite()+1);
				mod --;
			} else {
				possible --;
			}
		}
	}
	public void descendreSecurite (int mod){
		if (mod > this.entity.getEdSecurite()) {
			mod = this.entity.getEdSecurite();
		}
		this.entity.setEdSecurite(this.entity.getEdSecurite()-mod);
		this.entity.setEdEntretien(this.entity.getEdEntretien()+mod/4);
		this.entity.setEdSante(this.entity.getEdSante()+mod/4);
		this.entity.setEdRecherche(this.entity.getEdRecherche()+mod/4);
		this.entity.setEdTourisme(this.entity.getEdTourisme()+mod/4);
		mod = mod%4;
		if (mod > 0){
			this.entity.setEdEntretien(this.entity.getEdEntretien()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdSante(this.entity.getEdSante()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdRecherche(this.entity.getEdRecherche()+1);
			mod --;
		}
		if (mod > 0) {
			this.entity.setEdTourisme(this.entity.getEdTourisme()+1);
		}
	}
	public void modifierSecurite (int mod){
		if (mod > 0) {
			this.monterSecurite(mod);
		} else {
			this.descendreSecurite(-mod);
		}
	}
	public void monterEntretien (int mod){
		int possible = 4;					// S'il n'est plus possible de prendre des points d'education dans les autres secteurs, on arrete
		while (mod > 0 && possible > 0){
			if (this.entity.getEdSante() > 0 && mod > 0){		// On verifie pour chaque domaine si on peut prendre des points d'education
				this.entity.setEdSante(this.entity.getEdSante()-1);
				this.entity.setEdEntretien(this.entity.getEdEntretien()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdRecherche() > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				this.entity.setEdEntretien(this.entity.getEdEntretien()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdTourisme() > 0 && mod > 0){
				this.entity.setEdTourisme(this.entity.getEdTourisme()-1);
				this.entity.setEdEntretien(this.entity.getEdEntretien()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdSecurite() > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				this.entity.setEdEntretien(this.entity.getEdEntretien()+1);
				mod --;
			} else {
				possible --;
			}

		}
	}
	public void descendreEntretien (int mod){
		if (mod > this.entity.getEdEntretien()) {
			mod = this.entity.getEdEntretien();
		}
		this.entity.setEdEntretien(this.entity.getEdEntretien()-mod);
		this.entity.setEdSante(this.entity.getEdSante()+mod/4);
		this.entity.setEdRecherche(this.entity.getEdRecherche()+mod/4);
		this.entity.setEdTourisme(this.entity.getEdTourisme()+mod/4);
		this.entity.setEdSecurite(this.entity.getEdSecurite()+mod/4);
		mod = mod%4;
		if (mod > 0){
			this.entity.setEdSante(this.entity.getEdSante()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdRecherche(this.entity.getEdRecherche()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdTourisme(this.entity.getEdTourisme()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdSecurite(this.entity.getEdSecurite()+1);
			mod --;
		}
	}
	public void modifierEntretien (int mod){
		if (mod > 0) {
			this.monterEntretien(mod);
		} else {
			this.descendreEntretien(-mod);
		}
	}
	public void monterSante (int mod){
		int possible = 4;					// S'il n'est plus possible de prendre des points d'education dans les autres secteurs, on arrete
		while (mod > 0 && possible > 0){
			if (this.entity.getEdRecherche() > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				this.entity.setEdSante(this.entity.getEdSante()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdTourisme() > 0 && mod > 0){
				this.entity.setEdTourisme(this.entity.getEdTourisme()-1);
				this.entity.setEdSante(this.entity.getEdSante()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdSecurite() > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				this.entity.setEdSante(this.entity.getEdSante()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdEntretien() > 0 && mod > 0){
				this.entity.setEdEntretien(this.entity.getEdEntretien()-1);
				this.entity.setEdSante(this.entity.getEdSante()+1);
				mod --;
			} else {
				possible --;
			}
		}
	}
	public  void descendreSante (int mod){
		if (mod > this.entity.getEdSante()) {
			mod = this.entity.getEdSante();
		}
		this.entity.setEdSante(this.entity.getEdSante() - mod);
		this.entity.setEdRecherche(this.entity.getEdRecherche()+mod/4);
		this.entity.setEdTourisme(this.entity.getEdTourisme()+mod/4);
		this.entity.setEdSecurite(this.entity.getEdSecurite()+mod/4);
		this.entity.setEdEntretien(this.entity.getEdEntretien()+mod/4);
		mod = mod%4;
		if (mod > 0){
			this.entity.setEdRecherche(this.entity.getEdRecherche()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdTourisme(this.entity.getEdTourisme()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdSecurite(this.entity.getEdSecurite()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdEntretien(this.entity.getEdEntretien()+1);
			mod --;
		}
	}
	public  void modifierSante (int mod){
		if(mod > 0) {
			this.monterSante(mod);
		} else {
			this.descendreSante(-mod);
		}
	}
	public  void monterRecherche (int mod){
		int possible = 4;					// S'il n'est plus possible de prendre des points d'education dans les autres secteurs, on arrete
		while (mod > 0 && possible > 0){
			if (this.entity.getEdTourisme() > 0 && mod > 0){
				this.entity.setEdTourisme(this.entity.getEdTourisme()-1);
				this.entity.setEdRecherche(this.entity.getEdRecherche()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdSecurite() > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				this.entity.setEdRecherche(this.entity.getEdRecherche()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdEntretien() > 0 && mod > 0){
				this.entity.setEdEntretien(this.entity.getEdEntretien()-1);
				this.entity.setEdRecherche(this.entity.getEdRecherche()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdSante() > 0 && mod > 0){
				this.entity.setEdSante(this.entity.getEdSante()-1);
				this.entity.setEdRecherche(this.entity.getEdRecherche()+1);
				mod --;
			} else {
				possible --;
			}
		}
	}
	public  void descendreRecherche (int mod){
		if (mod > this.entity.getEdRecherche()) {
			mod = this.entity.getEdRecherche();
		}
		this.entity.setEdRecherche(this.entity.getEdRecherche()-mod);
		this.entity.setEdTourisme(this.entity.getEdTourisme()+mod/4);
		this.entity.setEdSecurite(this.entity.getEdSecurite()+mod/4);
		this.entity.setEdEntretien(this.entity.getEdEntretien()+mod/4);
		this.entity.setEdSante(this.entity.getEdSante()+mod/4);
		mod = mod%4;
		if (mod > 0){
			this.entity.setEdTourisme(this.entity.getEdTourisme()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdSecurite(this.entity.getEdSecurite()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdEntretien(this.entity.getEdEntretien()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdSante(this.entity.getEdSante()+1);
			mod --;
		}
	}
	public  void modifierRecherche (int mod){
		if(mod > 0) {
			this.monterRecherche(mod);
		} else {
			this.descendreRecherche(-mod);
		}
	}
	public void monterTourisme (int mod){
		int possible = 4;					// S'il n'est plus possible de prendre des points d'education dans les autres secteurs, on arrete
		while (mod > 0 && possible > 0){
			if (this.entity.getEdSecurite() > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				this.entity.setEdTourisme(this.entity.getEdTourisme()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdEntretien() > 0 && mod > 0){
				this.entity.setEdEntretien(this.entity.getEdEntretien()-1);
				this.entity.setEdTourisme(this.entity.getEdTourisme()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdSante() > 0 && mod > 0){
				this.entity.setEdSante(this.entity.getEdSante()-1);
				this.entity.setEdTourisme(this.entity.getEdTourisme()+1);
				mod --;
			} else {
				possible --;
			}
			if (this.entity.getEdRecherche() > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				this.entity.setEdTourisme(this.entity.getEdTourisme()+1);
				mod --;
			} else {
				possible --;
			}
		}
	}
	public  void descendreTourisme (int mod){
		if (mod > this.entity.getEdTourisme()) {
			mod = this.entity.getEdTourisme();
		}
		this.entity.setEdTourisme(this.entity.getEdTourisme()-mod);
		this.entity.setEdSecurite(this.entity.getEdSecurite()+mod/4);
		this.entity.setEdEntretien(this.entity.getEdEntretien()+mod/4);
		this.entity.setEdSante(this.entity.getEdSante()+mod/4);
		this.entity.setEdRecherche(this.entity.getEdRecherche()+mod/4);
		mod = mod%4;
		if (mod > 0){
			this.entity.setEdSecurite(this.entity.getEdSecurite()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdEntretien(this.entity.getEdEntretien()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdSante(this.entity.getEdSante()+1);
			mod --;
		}
		if (mod > 0){
			this.entity.setEdRecherche(this.entity.getEdRecherche()+1);
			mod --;
		}
	}
	public  void modifierTourisme (int mod){
		if(mod > 0) {
			this.monterTourisme(mod);
		} else {
			this.descendreTourisme(-mod);
		}
	}

	@Override
	public Education getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public static int getEdSecurite() {
		// TODO Auto-generated method stub
		return 0;
	}
}
