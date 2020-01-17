package fr.thomas_clement.utt;

import java.util.List;

/**
 * Interface Strategie qui permet de d�finir des niveaux de difficult�s
 */
public interface Strategy {
	
	/**
	 * M�thode abstraite pour faire une offre
	 * 
	 * @param player
	 * 			Player qui va faire une offre
	 * @return le num�ro de l'offre cach�e 
	 */
	abstract public int makeOffers(Player player);
	
	/**
	 * M�thode abstraite pour choisir une offre
	 * 
	 * @param players
	 * 			List de joueurs dont les offres peuvent �tre choisies
	 * 
	 * @param player
	 * 			Player qui est entrain de choisir une offre
	 * 
	 * @return tableau d'Object
	 */
	abstract public Object[] chooseOffers(List<Player> players, Player player);
	

}
