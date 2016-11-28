package fr.game.services.gameControllers;



import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import fr.Dao.BackupConstructionDAO;
import fr.Dao.BackupDAO;
import fr.entities.Backup;
import fr.entities.BackupConstruction;
import fr.entities.Budget;
import fr.entities.Population;
import fr.game.services.constructions.Commissariat;
//import fr.game.services.constructions.Ecole;
import fr.interfaces.IConstruction;
import fr.interfaces.IGameEntity;
import fr.interfaces.IEntity;

import fr.splExceptions.EntityException;

// il s'occupe des Game entities et de leur sauvegarde

public class EntitiesController {


	private final static String DES_COMMISARIAT = "commisariat";
	private final static String DES_ECOLE = "ecoles";


	private Map<String,IGameEntity> gameEntities = new HashMap<String,IGameEntity>();

	private BackupDAO backupDAO =null;
	private BackupConstructionDAO backupConstructionDAO= null;
	private Integer idBackup = null;

	public EntitiesController(Integer idBackup){
		this(new BackupDAO(),new BackupConstructionDAO(),idBackup);
	}
	
	public EntitiesController(BackupDAO backupDAO,BackupConstructionDAO backupConstructionDAO, Integer idBackup){
		super();
		this.backupDAO = backupDAO;
		this.backupConstructionDAO = backupConstructionDAO;
		this.idBackup = idBackup;
	}

	// ajoute a gameEntities
	public void addGameEntity(IGameEntity entity){
		this.gameEntities.put(entity.getName(), entity);
	}

	// supprime de gameEntities
	public void removeGameEntities(String key) throws EntityException{
		try{
			this.gameEntities.remove(key);
		}catch(NullPointerException e){
			throw new EntityException("Pas d'entité trouvé");
		}

	}

	// recupere un entite par sa clef
	public IGameEntity getGameEntity(String key) throws EntityException{
		IGameEntity entity = null;

		try{
			entity = this.gameEntities.get(key);
		}catch(NullPointerException e){
			throw new EntityException("Pas d'entité trouvé");
		}

		return entity;

	}

	public Integer getIdBackup() {
		return this.idBackup;
	}

	public void setIdBackup(Integer idBackup) {
		this.idBackup = idBackup;
	}

	// recupere toute les entites
	public Map<String,IGameEntity> getGameEntities(){
		return this.gameEntities;
	}

	// lance la sauvergarde de toutes les entitées
	public void saveGameEntities(){

		Iterator<String> itKey = this.getGameEntities().keySet().iterator();
		Iterator<IGameEntity> itValue = this.getGameEntities().values().iterator();

		while(itKey.next() != null){
			itValue.next().saveEntity();
		}
	}

	// lance la recuperation de toutes les entitées
	public List<IEntity> getGameEntitiesFromDao(Integer idBackup){
		List<IEntity> gameEntities = null;
		List<BackupConstruction> gameConstruction = null;
		IGameEntity entity = null;
		Backup backup = null;
		backup = this.backupDAO.get(idBackup);
		int ix = 0;

		// si on recupere corectement le backup
		if(backup != null){
			//on essaye de recuperer les constructions
			gameConstruction =  this.backupConstructionDAO.getAllByBackUp(backup);
			for(BackupConstruction backupConstruction : gameConstruction){
				gameEntities.add((IEntity) backupConstruction );
			}

			// si il y en a
//			if(gameEntities != null){
//				// on les stocke dans la map
//				for(IEntity backupConstruction : gameEntities){
//					if(((BackupConstruction) backupConstruction).getConstruction().getDesignation().equals(EntitiesController.DES_COMMISARIAT)){
//						entity = new Commissariat(backupConstruction,backupConstructionDAO);
//					}
//					if(backupConstruction.getConstruction().getDesignation().equals(EntitiesController.DES_ECOLE)){
//					//	entity = new Ecole(backupConstruction,backupConstructionDAO);
//					}
//					this.gameEntities.put(entity.getName(),(IGameEntity) backupConstruction );
//				}
//			}else{
//				gameEntities = null;	
//			}
			
			
			
		}
		return gameEntities;
	}

	//	public static int getNbActifs(){
	//		return getNbSalaries()+getNbCadres();
	//	}
	//	public static int getAttractivites(){
	//		int att = 0;
	//		for (int i = 0; i<constructions.size();i++) {
	//			att += constructions.get(i).getAttractivite();
	//		}
	//		return att;
	//	}
	//	public static int getBudgets(){
	//		int b= 0;
	//		for (int i = 0; i<constructions.size();i++) {
	//			b += constructions.get(i).getBudget();
	//		}
	//		return b;
	//	}
	//	public static int getPostesPourvus(){
	//		int p = 0;
	//		for (int i = 0; i<constructions.size();i++) {
	//			p += constructions.get(i).getPostePourvu();
	//		}
	//		return p;
	//	}
	//	public static void effectifs(){
	//		int total = Population.nbIndiv(Budget.getAgeTravail(), Budget.getAgeRetraite());
	//		int effectif = getPostesPourvus();
	//		int i = (int)Math.random()*constructions.size();
	//		while(total < effectif){		// S'il y a plus de postes que d'employes, on supprime des postes pouvus
	//			effectif -= constructions.get(i).retirerPersonnel();
	//			i += ((int)Math.random()*10)%constructions.size();
	//		}
	//	}
	//	public static void sabotage(int amplitude){
	//		int i = (int)Math.random()*constructions.size();
	//		constructions.get(i).modifierRisque(amplitude);
	//	}
	//
	//	// Getter & Setter
	//	public static List<Batiment> getConstructions() {
	//		return constructions;
	//	}
	//	public static void setConstructions(List<Batiment> constructions) {
	//		Batiment.constructions = constructions;
	//	}


}