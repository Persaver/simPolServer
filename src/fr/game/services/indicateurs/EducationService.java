package fr.game.services.indicateurs;

import java.util.List;

import fr.Dao.BackupConstructionDAO;
import fr.Dao.EducationDAO;
import fr.entities.Backup;
import fr.entities.BackupConstruction;
import fr.entities.Education;
import fr.game.services.gameControllers.AbstractGameEntity;
import fr.splExceptions.DAOException;
import fr.splExceptions.ServiceException;

public class EducationService extends AbstractGameEntity<Education, EducationDAO> {

	public EducationService(Education entity, EducationDAO entityDao) {
		super(entity, entityDao);
	}
		//Fonction a appeler quotidiennement
	public void actuEducationTot (Backup backup) throws ServiceException{
		List<BackupConstruction> backupConstructions = null;
		int edT = 0;
		try {
			backupConstructions = new BackupConstructionDAO().getAllByBackUpByConstruction(backup, BackupConstructionDAO.getIDECOLE());
			for (BackupConstruction element : backupConstructions){
				edT += element.getSpecificite().get("education");	// La clef des map Ecoles doit etre 'education'
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}

		this.entity.setEdTotale(edT);
	}
	
	public void recupEducation(PopulationService p, BudgetService b){
		int place = this.entity.getEdTotale();
		int etudiant = p.nbIndiv(0, b.getAgeTravail());
		if (place-etudiant < 0) {
			this.entity.setEdTotale((place*500/etudiant)*((int)(Math.sqrt(b.getAgeTravail())/4)));		// varie aussi selon l'age estime de fin d'etude. a 16 ans, facteur de 1, a 25 ans, facteur de 1.25, 36 ans, facteur de 1.5 ...
		} else {						// S'il y a plus de place, alors, les cours du soir et les formation d'ouvrier peuvent augmenter la productivite
			this.entity.setEdTotale(((100+(place-etudiant)/4)*5)*((int)(Math.sqrt(b.getAgeTravail())/4)));
		}
	}
	
	public void init(){
		this.entity.setRatioSecurite(100);
		this.entity.setRatioEntretien(100);
		this.entity.setRatioSante(100);
		this.entity.setRatioRecherche(100);
		this.entity.setRatioTourisme(100);
	}

	public void modifierSecurite(int mod){
		int a = 1;
		int sec = this.entity.getRatioSecurite();
		int ent = this.entity.getRatioEntretien();
		int san = this.entity.getRatioSante();
		int rech = this.entity.getRatioRecherche();
		int tour = this.entity.getRatioTourisme();
		int choix;
		if (mod < 0){	// Si on decide de retirer des valeurs
			a = -1;
			mod = (-mod > tour) ? tour : -mod;	// Ici, on verifie que le retrait ne peut pas mettre l'indicateur en negatif
		}
		while (mod > 0){
			choix = (int)Math.random()*400;
			if (choix < ent){
				this.entity.setRatioEntretien(ent - a);
				this.entity.setRatioSecurite(sec +a);
			} else {
				choix -= ent;
				if (choix < san){
					this.entity.setRatioSante(san-a);
					this.entity.setRatioSecurite(sec +a);
				} else {
					choix -= san;
					if (choix < rech){
						this.entity.setRatioRecherche(rech-a);
						this.entity.setRatioSecurite(sec +a);
					} else {
						choix -= rech;
						if (choix < tour){
							this.entity.setRatioTourisme(tour-a);
							this.entity.setRatioSecurite(sec +a);
						}
					}
				}
			}
			--mod;
		}
	}
	public void modifierEntretien(int mod){
		int a = 1;
		int sec = this.entity.getRatioSecurite();
		int ent = this.entity.getRatioEntretien();
		int san = this.entity.getRatioSante();
		int rech = this.entity.getRatioRecherche();
		int tour = this.entity.getRatioTourisme();
		int choix;
		if (mod < 0){	// Si on decide de retirer des valeurs
			a = -1;
			mod = (-mod > tour) ? tour : -mod;	// Ici, on verifie que le retrait ne peut pas mettre l'indicateur en negatif
		}
		while (mod > 0){
			choix = (int)Math.random()*400;
			if (choix < sec){
				this.entity.setRatioSecurite(sec - a);
				this.entity.setRatioEntretien(ent +a);
			} else {
				choix -= sec;
				if (choix < san){
					this.entity.setRatioSante(san-a);
					this.entity.setRatioEntretien(ent +a);
				} else {
					choix -= san;
					if (choix < rech){
						this.entity.setRatioRecherche(rech-a);
						this.entity.setRatioEntretien(ent +a);
					} else {
						choix -= rech;
						if (choix < tour){
							this.entity.setRatioTourisme(tour-a);
							this.entity.setRatioEntretien(ent +a);
						}
					}
				}
			}
			--mod;
		}
	}
	
	public void modifierSante(int mod){
		int a = 1;
		int sec = this.entity.getRatioSecurite();
		int ent = this.entity.getRatioEntretien();
		int san = this.entity.getRatioSante();
		int rech = this.entity.getRatioRecherche();
		int tour = this.entity.getRatioTourisme();
		int choix;
		if (mod < 0){	// Si on decide de retirer des valeurs
			a = -1;
			mod = (-mod > tour) ? tour : -mod;	// Ici, on verifie que le retrait ne peut pas mettre l'indicateur en negatif
		}
		while (mod > 0){
			choix = (int)Math.random()*400;
			if (choix < sec){
				this.entity.setRatioSecurite(sec - a);
				this.entity.setRatioSante(san +a);
			} else {
				choix -= sec;
				if (choix < ent){
					this.entity.setRatioEntretien(ent-a);
					this.entity.setRatioSante(san +a);
				} else {
					choix -= ent;
					if (choix < rech){
						this.entity.setRatioRecherche(rech-a);
						this.entity.setRatioSante(san +a);
					} else {
						choix -= rech;
						if (choix < tour){
							this.entity.setRatioTourisme(tour-a);
							this.entity.setRatioSante(san +a);
						}
					}
				}
			}
			--mod;
		}
	}
	public void modifierRecherche(int mod){
		int a = 1;
		int sec = this.entity.getRatioSecurite();
		int ent = this.entity.getRatioEntretien();
		int san = this.entity.getRatioSante();
		int rech = this.entity.getRatioRecherche();
		int tour = this.entity.getRatioTourisme();
		int choix;
		if (mod < 0){	// Si on decide de retirer des valeurs
			a = -1;
			mod = (-mod > tour) ? tour : -mod;	// Ici, on verifie que le retrait ne peut pas mettre l'indicateur en negatif
		}
		while (mod > 0){
			choix = (int)Math.random()*400;
			if (choix < sec){
				this.entity.setRatioSecurite(sec - a);
				this.entity.setRatioRecherche(rech + a);
			} else {
				choix -= sec;
				if (choix < ent){
					this.entity.setRatioEntretien(ent - a);
					this.entity.setRatioRecherche(rech + a);
				} else {
					choix -= ent;
					if (choix < san){
						this.entity.setRatioSante(san - a);
						this.entity.setRatioRecherche(rech + a);
					} else {
						choix -= san;
						if (choix < tour){
							this.entity.setRatioTourisme(tour - a);
							this.entity.setRatioRecherche(rech + a);
						}
					}
				}
			}
			--mod;
		}
	}
	public void modifierTourisme(int mod){
		int a = 1;
		int sec = this.entity.getRatioSecurite();
		int ent = this.entity.getRatioEntretien();
		int san = this.entity.getRatioSante();
		int rech = this.entity.getRatioRecherche();
		int tour = this.entity.getRatioTourisme();
		int choix;
		if (mod < 0){	// Si on decide de retirer des valeurs
			a = -1;
			mod = (-mod > tour) ? tour : -mod;	// Ici, on verifie que le retrait ne peut pas mettre l'indicateur en negatif
		}
		while (mod > 0){	// Avec cette methode, on garde vaguement les meme ratios
			choix = (int)Math.random()*400;
			if (choix < sec){
				this.entity.setRatioSecurite(sec - a);
				this.entity.setRatioTourisme(tour + a);
			} else {
				choix -= sec;
				if (choix < ent){
					this.entity.setRatioEntretien(ent - a);
					this.entity.setRatioTourisme(tour + a);
				} else {
					choix -= ent;
					if (choix < san){
						this.entity.setRatioSante(san - a);
						this.entity.setRatioTourisme(tour + a);
					} else {
						choix -= san;
						if (choix < tour){
							this.entity.setRatioRecherche(rech - a);
							this.entity.setRatioTourisme(tour + a);
						}
					}
				}
			}
			--mod;
		}
	}
	public void distibution (){
		this.entity.setEdSecurite(this.entity.getEdTotale()*this.entity.getRatioSecurite()/100);
		this.entity.setEdEntretien(this.entity.getEdTotale()*this.entity.getRatioEntretien()/100);
		this.entity.setEdSante(this.entity.getEdTotale()*this.entity.getRatioSante()/100);
		this.entity.setEdRecherche(this.entity.getEdTotale()*this.entity.getRatioRecherche()/100);
		this.entity.setEdTourisme(this.entity.getEdTotale()*this.entity.getRatioTourisme()/100);
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
