package fr.thomas_clement.utt;

import java.util.List;

/**
 * Interface Strategie qui permet de d�finir des niveaux de difficult�s
 */
public interface Strategy {
	
	/**
	 * M�thode abstraite pour faire une offre
	 */
	abstract public int makeOffers(Player player);
	
	/**
	 * M�thode abstraite pour choisir une offre
	 */
	abstract public Object[] chooseOffers(List<Player> players, Player player);
	

}
