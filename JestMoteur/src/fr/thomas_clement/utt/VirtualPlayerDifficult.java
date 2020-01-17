package fr.thomas_clement.utt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Type difficult� HARD du joueur virtuel pour choisir les meilleurs cartes qui impl�mente l'interface strat�gie
 */
public class VirtualPlayerDifficult implements Strategy{

	/**
	 * Red�finition de la m�thode makeOffers permettant de mettre l'offre cach�e de mani�re al�atoire 
	 * @param player
	 * 				Un joueur
	 * @return le num�ro de l'offre cach�e
	 */
	@Override
	public int makeOffers(Player player) {
		
		int offerHidden = (new Random().nextInt((2 - 1) + 1) + 1);
		return offerHidden;
		
	}

	/**
	 * Choisir une offre de mani�re a toujours favoriser les piques et les tr�fles de mani�re � maximiser le score
	 * @param players
	 * 				La liste des joueurs dont le joueur peut choisir
	 * @param p
	 * 				le joueur qui est en train de choisir
	 * @return tableau d'objet (en 1er : le joueur qu'il a choisi, en 2eme l'offre choisi du joueur qu'il a choisit)
	 */
	@Override
	public Object[] chooseOffers(List<Player> players, Player p) {
		
		List<Player> bestPlayer = new ArrayList<>();
		bestPlayer.add(players.get(0));
		int bestOffer = new Random().nextInt((2 - 1) + 1) + 1;
		
		for (int k = 0; k < players.size(); k++) {
			if(players.get(k).getHand().getCards().get(0).getShape().equals(Shape.SPADES) || players.get(k).getHand().getCards().get(0).getShape().equals(Shape.CLUBS)) {
				bestOffer = 1;
				bestPlayer.add(players.get(k));
			} else {
				bestOffer = 2;
				bestPlayer.add(players.get(k));
			}
		}
		
		
						
		
		return new Object[] {bestPlayer.get(0), bestOffer};
	}

	

}
