package fr.interfaces;

import java.util.List;

import fr.entities.Budget;
import fr.entities.Population;

public interface IConstruction extends IGameEntity{

	public void ameliore();

	public int getPostePourvu();

	public void prisePostes();

	public void ajoutPoste();

	public static void usures() {
	}


}
