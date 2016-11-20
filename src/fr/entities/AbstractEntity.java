package fr.entities;

import java.io.Serializable;

import com.google.gson.Gson;

import fr.interfaces.IEntity;
import fr.interfaces.IJsonEntity;

public class AbstractEntity<T> implements Serializable,IJsonEntity,IEntity{
	protected T id;
	
	public AbstractEntity(){
		this.id = null;
	}
	
	public AbstractEntity(T id){
		super();
		this.id = id;
	}
	
	public T getId(){
		return id;
	};
	public String toJson(){
		Gson gs = new Gson();
		return gs.toJson(this);
		
	}
	
}
