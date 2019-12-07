package fr.thomas_clement.utt;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		//System.out.println(Shape.CLUBS.ordinal());
//		int max = 4;
//		int min = 0;
//		
//		Random r = new Random();
//		System.out.println(r.nextInt((max - min) + 1) + min);
//		
//		int offerHidden = new Random().nextInt((1 - 0) + 1) + 0;
//		System.out.println(offerHidden);
		
		Game party = new Game();
		
		party.initializeDeck(); //Create cards and add them to a packet named Deck
		
		party.initializePlayers(); // How many players ? How many REAL and VIRTUAL Players ? Which Strategy ?
		
		party.initializeTrophies(); // From the deck, it defines the trophies
		
		System.out.println(party.getTrophies());
		
		party.playRounds();
		
		System.out.println(party.getDeck());
		//System.out.println(party.getPlayers());
		System.out.println(party.getTrophies());
		
		for (int i = 0; i < party.getPlayers().size(); i++) {
			System.out.println(party.getPlayers().get(i).getHand());
		}
		
		
	}

}
