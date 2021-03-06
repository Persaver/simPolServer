package fr.game.services.gameControllers;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fr.Dao.BackupConstructionDAO;
import fr.Dao.BackupDAO;
import fr.entities.Backup;
import fr.entities.BackupConstruction;
import fr.interfaces.IEntity;
import fr.interfaces.IGameEntity;
import fr.splExceptions.DAOException;
import fr.splExceptions.EntityException;
import fr.splExceptions.ServiceException;

// il s'occupe des Game entities et de leur sauvegarde

public class EntitiesController {


	private final static String DES_COMMISARIAT = "commisariat";
	private final static String DES_ECOLE = "ecoles";


	private Map<String,IEntity> gameEntities = new HashMap<String,IEntity>();

	private BackupDAO backupDAO =new BackupDAO();
	private BackupConstructionDAO backupConstructionDAO= new BackupConstructionDAO();
	private Integer idBackup = null;

	public EntitiesController(){
		this(null);
	}

	public EntitiesController(Integer idBackup){
		this.idBackup = idBackup;
	}

	// ajoute a gameEntities
	public IEntity addGameEntity(IEntity entity) throws ServiceException{
		BackupConstruction bckCons = null;
		try{

			if( entity instanceof BackupConstruction){
				bckCons=(BackupConstruction)entity;
				try {
					this.backupConstructionDAO.save(bckCons);
				} catch (DAOException e) {
					throw new ServiceException(e.getMessage());
				}
			}

			this.gameEntities.put(bckCons.getClass().getName()+bckCons.getId(), entity);
		}catch(NullPointerException e ){
			throw new ServiceException(e.getMessage());
		}
		return bckCons;
	}

	// supprime de gameEntities
	public void removeGameEntities(String key) throws EntityException{
		try{
			this.gameEntities.remove(key);
		}catch(NullPointerException e){
			throw new EntityException("Pas d'entité trouvé");
		}

	}

	// ajoute a gameEntities
	public IEntity saveGameEntity(IEntity entity) throws ServiceException{
		BackupConstruction bckCons = null;
		try{

			if( entity instanceof BackupConstruction){
				bckCons=(BackupConstruction)entity;
				try {
					this.backupConstructionDAO.save(bckCons);
				} catch (DAOException e) {
					throw new ServiceException(e.getMessage());
				}
			}

			this.gameEntities.put(bckCons.getClass().getName()+bckCons.getId(), entity);
		}catch(NullPointerException e ){
			throw new ServiceException(e.getMessage());
		}
		return bckCons;
	}


	// recupere un entite par sa clef
	// clef = class de l'entité+id ex: user1
	public IEntity getGameEntity(Integer id) throws ServiceException{
		IEntity entity = null;

		try {
			entity = this.backupConstructionDAO.get(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		if(entity == null){
			throw new ServiceException("Pas d'entité trouvé");
		}else{
			this.gameEntities.put(entity.getClass().getName()+id, entity);

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
	public Map<String, IEntity> getGameEntities(){
		return this.gameEntities;
	}

	// lance la sauvergarde de toutes les entitées
	public void saveGameEntities() throws ServiceException{

		Iterator<String> itKey = this.getGameEntities().keySet().iterator();
		Iterator<IEntity> itValue = this.getGameEntities().values().iterator();
		IEntity entity = null;

		while(itKey.next() != null){
			entity = itValue.next();
			if(entity != null){
				if(entity instanceof BackupConstruction){
					try {
						this.backupConstructionDAO.save((BackupConstruction)entity);
					} catch (DAOException e) {
						throw new ServiceException(e.getMessage());
					}
				}
			}
		}
	}

	public IEntity updategGameEntity(IEntity upEntity) throws ServiceException{
		IEntity entity = null;

		if(upEntity == null){
			throw new ServiceException("upEntity == null");
		}
		try{
			entity = new BackupConstructionDAO().update((BackupConstruction)upEntity);

		}catch(DAOException e){
			throw new ServiceException(e.getMessage());
		}

		return entity;
	}
	// lance la recuperation de toutes les entitées
	public List<IEntity> getGameEntitiesFromDao(Integer idBackup) throws ServiceException{
		List<IEntity> gameEntities = null;
		List<BackupConstruction> gameConstruction = null;
		IGameEntity entity = null;
		Backup backup = null;
		try {
			backup = this.backupDAO.get(idBackup);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		int ix = 0;

		// si on recupere corectement le backup
		if(backup != null){
			//on essaye de recuperer les constructions
			try {
				gameConstruction =  this.backupConstructionDAO.getAllByBackUp(backup);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				throw new ServiceException(e.getMessage());
			}
			if((gameConstruction != null) && !gameConstruction.isEmpty()){
				gameEntities=new ArrayList<IEntity>();
				for(BackupConstruction backupConstruction : gameConstruction){
					gameEntities.add(backupConstruction );
					this.getGameEntities().put(backupConstruction.getClass().getName()+backupConstruction.getId(),backupConstruction);
				}
			} else {
				throw new ServiceException("Pas de construction pour ce backup");
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