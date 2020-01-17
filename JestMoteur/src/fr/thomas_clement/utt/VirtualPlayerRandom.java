package fr.thomas_clement.utt;

import java.util.List;
import java.util.Random;

/**
 * Type difficulté EASY du joueur virtuel pour choisir aléatoirement une offre qui implémente l'interface stratégie
 */
public class VirtualPlayerRandom implements Strategy{
	
	/**
	 * Redéfinition de la méthode makeOffers permettant de mettre l'offre cachée de manière aléatoire 
	 * @param player
	 * 				Un joueur
	 * @return le numéro de l'offre cachée
	 */
	@Override
	public int makeOffers(Player player) {
		int offerHidden = new Random().nextInt((2 - 1) + 1) + 1;
		return offerHidden;
		
	}
	
	/**
	 * Choisir une offre de manière aléatoire
	 * @param tabPlayers
	 * 				La liste des joueurs dont le joueur peut choisir
	 * @param p
	 * 				le joueur qui est en train de choisir
	 * @return tableau d'objet (en 1er : le joueur qu'il a choisi, en 2eme l'offre choisi du joueur qu'il a choisit)
	 */
	@Override
	public Object[] chooseOffers(List<Player> tabPlayers, Player p) {
		
		Player playerChosen = tabPlayers.get((new Random().nextInt(((tabPlayers.size()-1) - 0) + 1) + 0));
		int offerTaken = new Random().nextInt((2 - 1) + 1) + 1;
		
		
		return new Object[] {playerChosen, offerTaken};
		
	}

	


}
