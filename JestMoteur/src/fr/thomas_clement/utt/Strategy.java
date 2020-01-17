package fr.thomas_clement.utt;

import java.util.List;

/**
 * Interface Strategie qui permet de définir des niveaux de difficultés
 */
public interface Strategy {
	
	/**
	 * Méthode abstraite pour faire une offre
	 * 
	 * @param player
	 * 			Player qui va faire une offre
	 * @return le numéro de l'offre cachée 
	 */
	abstract public int makeOffers(Player player);
	
	/**
	 * Méthode abstraite pour choisir une offre
	 * 
	 * @param players
	 * 			List de joueurs dont les offres peuvent être choisies
	 * 
	 * @param player
	 * 			Player qui est entrain de choisir une offre
	 * 
	 * @return tableau d'Object
	 */
	abstract public Object[] chooseOffers(List<Player> players, Player player);
	

}
