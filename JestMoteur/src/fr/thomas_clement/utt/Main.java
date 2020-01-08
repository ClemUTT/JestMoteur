package fr.thomas_clement.utt;

import java.util.Random;

import fr.thomas_clement.controleur.*;
import fr.thomas_clement.modele.*;
import fr.thomas_clement.vue.*;

public class Main {

	public static void main(String[] args) {
		
		System.out.println((new Random().nextInt((2 - 1) + 1) + 1));
		
		AbstractGame party = new Game();
		AbstractControleur controleur = new GameControleur(party);
		Vue vue = new Vue(controleur);
		
		party.addObserver(vue);
		
		party.initializePlayers(); // How many players ? How many REAL and VIRTUAL Players ? Which Strategy ?
		
		party.initializeDeck(); //Create cards and add them to a packet named Deck
		
		//party.initializeTrophies(); // From the deck, it defines the trophies
		
		//System.out.println(party.getTrophies());
		
		//party.playRounds();
		
//		System.out.println(party.getDeck());
//		//System.out.println(party.getPlayers());
//		System.out.println(party.getTrophies());
//		
//		for (int i = 0; i < party.getPlayers().size(); i++) {
//			System.out.println(party.getPlayers().get(i).getHand());
//		}
		
		
	}

}
