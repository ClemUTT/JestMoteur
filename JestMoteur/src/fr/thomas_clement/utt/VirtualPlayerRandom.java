package fr.thomas_clement.utt;

import java.util.List;
import java.util.Random;

/**
 * Type difficult� EASY du joueur virtuel pour choisir al�atoirement une offre qui impl�mente l'interface strat�gie
 */
public class VirtualPlayerRandom implements Strategy{
	
	/**
	 * Red�finition de la m�thode makeOffers permettant de mettre l'offre cach�e de mani�re al�atoire 
	 * @param player
	 * 				Un joueur
	 * @return le num�ro de l'offre cach�e
	 */
	@Override
	public int makeOffers(Player player) {
		int offerHidden = new Random().nextInt((2 - 1) + 1) + 1;
		return offerHidden;
		
	}
	
	/**
	 * Choisir une offre de mani�re al�atoire
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
