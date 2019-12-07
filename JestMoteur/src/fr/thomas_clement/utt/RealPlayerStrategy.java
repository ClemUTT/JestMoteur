package fr.thomas_clement.utt;

import java.util.List;
import java.util.Scanner;

public class RealPlayerStrategy implements Strategy{

	@Override
	public int makeOffers(Player p) {
		
		for (int i = 0; i < p.getHand().getCards().size(); i++) {
			System.out.println("offre " + (i+1) + " : " + p.getHand().getCards().get(i));
		}
		
		Scanner sc = new Scanner(System.in);
		int answer = sc.nextInt();
		
		
		return answer;
	}

	
	@Override
	public Object[] chooseOffers(List<Player> players, Player p) {
		
		Scanner sc = new Scanner(System.in);
		
		String nameAnswer = sc.next();
		Player playerTaken = null;
		
		
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
