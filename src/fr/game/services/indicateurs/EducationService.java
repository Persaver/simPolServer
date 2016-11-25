package fr.game.services.indicateurs;

import fr.Dao.EnseignementDAO;
import fr.entities.Education;
import fr.game.services.gameControlor.AbstractGameEntity;

public abstract class EducationService extends AbstractGameEntity<EducationService, EnseignementDAO> {
	
	public EducationService(EducationService entity, EnseignementDAO entityDao) {
		super(entity, entityDao);
		this.entity = (EducationService) this.getEntity();
	}

	public void recupEducation(){
		int place = this.entity.getEdTotale();
		int etudiant = Population.nbIndiv(0, Budget.getAgeTravail());
		if (place-etudiant < 0)		// S'il n'y a pas assez de place pour les etudiant, l'education en pati
			edTotale = (place*500/etudiant)*((int)(Math.sqrt(Budget.getAgeTravail())/4));		// varie aussi selon l'age estime de fin d'etude. a 16 ans, facteur de 1, ï¿½ 25 ans, facteur de 1.25, 36 ans, facteur de 1.5 ...
		else {						// S'il y a plus de place, alors, les cours du soir et les formation d'ouvrier peuvent augmenter la productivite
			edTotale = ((100+(place-etudiant)/4)*5)*((int)(Math.sqrt(Budget.getAgeTravail())/4));
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
			} else
				possible --;
			if (this.entity.getEdSante() > 0 && mod > 0){
				this.entity.setEdSante(this.entity.getEdSante()-1);
				this.entity.setEdSecurite(this.entity.getEdSecurite()+1);
				mod --;
			} else
				possible --;
			if (this.entity.getEdRecherche() > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				this.entity.setEdSecurite(this.entity.getEdSecurite()+1);
				mod --;
			} else
				possible --;
			if (this.entity.getEdTourisme() > 0 && mod > 0){
				this.entity.setEdTourisme(this.entity.getEdTourisme()-1);
				this.entity.setEdSecurite(this.entity.getEdSecurite()+1);
				mod --;
			} else
				possible --;
		}
	}
	public void descendreSecurite (int mod){
		if (mod > this.entity.getEdSecurite())
			mod = this.entity.getEdSecurite();
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
		if (mod > 0)
			this.entity.setEdTourisme(this.entity.getEdTourisme()+1);
	}		
	public void modifierSecurite (int mod){
		if (mod > 0)
			monterSecurite(mod);
		else
			descendreSecurite(-mod);
	}
	public void monterEntretien (int mod){
		int possible = 4;					// S'il n'est plus possible de prendre des points d'education dans les autres secteurs, on arrete
		while (mod > 0 && possible > 0){	
			if (this.entity.getEdSante() > 0 && mod > 0){		// On verifie pour chaque domaine si on peut prendre des points d'education
				this.entity.setEdSante(this.entity.getEdSante()-1);
				this.entity.setEdEntretien(this.entity.getEdEntretien()+1);
				mod --;
			} else
				possible --;
			if (this.entity.getEdRecherche() > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				this.entity.setEdEntretien(this.entity.getEdEntretien()+1);
				mod --;
			} else
				possible --;
			if (this.entity.getEdTourisme() > 0 && mod > 0){
				this.entity.setEdTourisme(this.entity.getEdTourisme()-1);
				this.entity.setEdEntretien(this.entity.getEdEntretien()+1);
				mod --;
				// TODO Continuer la traduction
			} else
				possible --;
			if (edSecurite > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				edEntretien ++;
				mod --;
			} else
				possible --;
			
		}
	}
	public void descendreEntretien (int mod){
		if (mod > edEntretien)
			mod = edEntretien;
		this.entity.setEdEntretien(this.entity.getEdEntretien()-mod);
		this.entity.setEdSante(this.entity.getEdSante()+mod/4);
		this.entity.setEdRecherche(this.entity.getEdRecherche()+mod/4);
		this.entity.setEdTourisme(this.entity.getEdTourisme()+mod/4);
		this.entity.setEdSecurite(this.entity.getEdSecurite()+mod/4);
		mod = mod%4;
		if (mod > 0){
			edSante ++;
			mod --;
		}
		if (mod > 0){
			edRecherche ++;
			mod --;
		}
		if (mod > 0){
			edTourisme ++;
			mod --;
		}
		if (mod > 0){
			edSecurite ++;
			mod --;
		}
	}
	public static void modifierEntretien (int mod){
		if (mod > 0)
			monterEntretien(mod);
		else
			descendreEntretien(-mod);
	}
	public static void monterSante (int mod){
		int possible = 4;					// S'il n'est plus possible de prendre des points d'education dans les autres secteurs, on arrete
		while (mod > 0 && possible > 0){
			if (edRecherche > 0 && mod > 0){
				edRecherche --;
				edSante ++;
				mod --;
			} else
				possible --;
			if (edTourisme > 0 && mod > 0){
				edTourisme --;
				edSante ++;
				mod --;
			} else
				possible --;
			if (edSecurite > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				edSante ++;
				mod --;
			} else
				possible --;
			if (edEntretien > 0 && mod > 0){
				edEntretien --;
				edSante ++;
				mod --;
			} else
				possible --;
		}
	}
	public static void descendreSante (int mod){
		if (mod > edSante)
			mod = edSante;
		edSante -= mod;
		this.entity.setEdRecherche(this.entity.getEdRecherche()+mod/4)
		this.entity.setEdTourisme(this.entity.getEdTourisme()+mod/4);
		this.entity.setEdSecurite(this.entity.getEdSecurite()+mod/4);
		edEntretien += mod/4;
		mod = mod%4;
		if (mod > 0){
			edRecherche ++;
			mod --;
		}
		if (mod > 0){
			edTourisme ++;
			mod --;
		}
		if (mod > 0){
			edSecurite ++;
			mod --;
		}
		if (mod > 0){
			edEntretien ++;
			mod --;
		}
	}
	public static void modifierSante (int mod){
		if(mod > 0)
			monterSante(mod);
		else
			descendreSante(-mod);
	}
	public static void monterRecherche (int mod){
		int possible = 4;					// S'il n'est plus possible de prendre des points d'education dans les autres secteurs, on arrete
		while (mod > 0 && possible > 0){
			if (edTourisme > 0 && mod > 0){
				edTourisme --;
				edRecherche ++;
				mod --;
			} else
				possible --;
			if (edSecurite > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				edRecherche ++;
				mod --;
			} else
				possible --;
			if (edEntretien > 0 && mod > 0){
				edEntretien --;
				edRecherche ++;
				mod --;
			} else
				possible --;
			if (edSante > 0 && mod > 0){
				edSante --;
				edRecherche ++;
				mod --;
			} else
				possible --;
		}
	}
	public static void descendreRecherche (int mod){
		if (mod > edRecherche)
			mod = edRecherche;
		edRecherche -= mod;
		this.entity.setEdTourisme(this.entity.getEdTourisme()+mod/4);
		this.entity.setEdSecurite(this.entity.getEdSecurite()+mod/4);
		edEntretien += mod/4;
		this.entity.setEdSante(this.entity.getEdSante()+mod/4)
		mod = mod%4;
		if (mod > 0){
			edTourisme ++;
			mod --;
		}
		if (mod > 0){
			edSecurite ++;
			mod --;
		}
		if (mod > 0){
			edEntretien ++;
			mod --;
		}
		if (mod > 0){
			edSante ++;
			mod --;
		}
	}
	public static void modifierRecherche (int mod){
		if(mod > 0)
			monterRecherche(mod);
		else
			descendreRecherche(-mod);
	}
	public void monterTourisme (int mod){
		int possible = 4;					// S'il n'est plus possible de prendre des points d'education dans les autres secteurs, on arrete
		while (mod > 0 && possible > 0){
			if (edSecurite > 0 && mod > 0){
				this.entity.setEdRecherche(this.entity.getEdRecherche()-1);
				edTourisme ++;
				mod --;
			} else
				possible --;
			if (edEntretien > 0 && mod > 0){
				edEntretien --;
				edTourisme ++;
				mod --;
			} else
				possible --;
			if (edSante > 0 && mod > 0){
				edSante --;
				edTourisme ++;
				mod --;
			} else
				possible --;
			if (edRecherche > 0 && mod > 0){
				edRecherche --;
				edTourisme ++;
				mod --;
			} else
				possible --;
		}
	}
	public static void descendreTourisme (int mod){
		if (mod > edTourisme)
			mod = edTourisme;
		edTourisme -= mod;
		this.entity.setEdSecurite(this.entity.getEdSecurite()+mod/4);
		edEntretien += mod/4;
		this.entity.setEdSante(this.entity.getEdSante()+mod/4)
		this.entity.setEdRecherche(this.entity.getEdRecherche()+mod/4)
		mod = mod%4;
		if (mod > 0){
			edSecurite ++;
			mod --;
		}
		if (mod > 0){
			edEntretien ++;
			mod --;
		}
		if (mod > 0){
			edSante ++;
			mod --;
		}
		if (mod > 0){
			edRecherche ++;
			mod --;
		}
	}
	public static void modifierTourisme (int mod){
		if(mod > 0)
			monterTourisme(mod);
		else
			descendreTourisme(-mod);
	}

	public static void setEdTotale(int tot){
		edTotale = tot;
	}
	public static void afficher(){
		System.out.println("Total = " + edTotale);
		System.out.println("Securite = " + edSecurite);
		System.out.println("Entretien = " + edEntretien);
		System.out.println("Sante = " + edSante);
		System.out.println("Recherche = " +edRecherche);
		System.out.println("Tourisme = " + edTourisme);
		
	}

	public static int getEdSecurite() {
		return edSecurite;
	}

	public static void setEdSecurite(int edSecurite) {
		EducationService.edSecurite = edSecurite;
	}

	public static int getEdEntretien() {
		return edEntretien;
	}

	public static void setEdEntretien(int edEntretien) {
		EducationService.edEntretien = edEntretien;
	}

	public static int getEdSante() {
		return edSante;
	}

	public static void setEdSante(int edSante) {
		EducationService.edSante = edSante;
	}

	public static int getEdRecherche() {
		return edRecherche;
	}

	public static void setEdRecherche(int edRecherche) {
		EducationService.edRecherche = edRecherche;
	}

	public static int getEdTourisme() {
		return edTourisme;
	}

	public static void setEdTourisme(int edTourisme) {
		EducationService.edTourisme = edTourisme;
	}

	public static int getEdTotale() {
		return edTotale;
	}

}
