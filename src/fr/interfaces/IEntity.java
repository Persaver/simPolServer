package fr.interfaces;

public interface IEntity {
	public void setEntity(IEntity entity);
	public void saveEntity();
	public IEntity getEntity();
	public boolean isModify();
	public void setModify();
}
