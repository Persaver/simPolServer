package fr.entities;

import java.io.Serializable;

import com.google.gson.Gson;

import fr.interfaces.IEntity;
import fr.interfaces.IJsonEntity;

public abstract class AbstractEntity<T> implements Serializable,IJsonEntity,IEntity{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected Integer id;

	protected AbstractEntity(){
		this(null);
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
