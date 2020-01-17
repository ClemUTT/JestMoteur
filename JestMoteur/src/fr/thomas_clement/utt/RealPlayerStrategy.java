package fr.thomas_clement.utt;

import java.util.List;
import java.util.Scanner;

/**
 * Type de stratégie du joueur réél qui implémente l'interface Strategy
 */
public class RealPlayerStrategy implements Strategy{
	
	/**
	 * Affiche les offres du joueur et va définir quelle offre il décide de cacher
	 * @param p
	 * 				Un joueur
	 * @return le numéro de l'offre qu'il a décidé de cacher 
	 */
	public int makeOffers(Player p) {
		
		//Display its offers
		for (int i = 0; i < p.getHand().getCards().size(); i++) {
			System.out.println("offre " + (i+1) + " : " + p.getHand().getCards().get(i));
		}
		
		Scanner sc = new Scanner(System.in);
		int answer = sc.nextInt();
		
		
		return answer;
	}

	
	/**
	 * Choisi un joueur et son offre parmi la liste des joueurs dont il peut choisir l'offre
	 * @param players
	 * 				La liste des joueurs dont le joueur peut choisir
	 * @param p
	 * 				le joueur qui est en train de choisir
	 * @return tableau d'objet (en 1er : le joueur qu'il a choisi, en 2eme l'offre choisi du joueur qu'il a choisit)
	 */
	@Override
	public Object[] chooseOffers(List<Player> players, Player p) {
		
		Scanner sc = new Scanner(System.in);
		
		String nameAnswer = sc.next();
		Player playerTaken = null;
		
		//The String chosen player is casted ad a Player
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).getNickname().equalsIgnoreCase(nameAnswer)) {
				playerTaken = players.get(i);
			}
		}
		
		
		System.out.println("Choisissez l'offre que vous souhaitez prendre du joueur " + playerTaken.getNickname() + " : ");
		
		
		
		int offerAnswer = sc.nextInt();
		
		Object[] tab = {playerTaken, offerAnswer};
		
		return tab;
		
	}

	
	
	
	
	
	

}
