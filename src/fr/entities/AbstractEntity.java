package fr.entities;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import fr.interfaces.IEntity;
import fr.interfaces.IJsonEntity;

public abstract class AbstractEntity<T> implements Serializable, IJsonEntity, IEntity{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	protected Integer id;

	private static final Logger LOG = LogManager.getLogger();

	{
		LOG.debug(" Creation entity {} ",this.getClass().getName());
	}
	public AbstractEntity(){
		
	}

	public AbstractEntity(Integer id){
		this.id = id;
	}

	@Override
	public String toJson(){
		Gson gs = new Gson();
		return gs.toJson(this);
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
