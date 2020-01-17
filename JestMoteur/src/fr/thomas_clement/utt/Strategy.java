package fr.thomas_clement.utt;

import java.util.List;

/**
 * Interface Strategie qui permet de définir des niveaux de difficultés
 */
public interface Strategy {
	
	/**
	 * Méthode abstraite pour faire une offre
	 */
	abstract public int makeOffers(Player player);
	
	/**
	 * Méthode abstraite pour choisir une offre
	 */
	abstract public Object[] chooseOffers(List<Player> players, Player player);
	

}
