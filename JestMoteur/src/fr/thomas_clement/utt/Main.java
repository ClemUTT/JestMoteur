package fr.thomas_clement.utt;

import fr.thomas_clement.controleur.AbstractControleur;
import fr.thomas_clement.controleur.GameControleur;
import fr.thomas_clement.modele.AbstractGame;
import fr.thomas_clement.modele.Game;
import fr.thomas_clement.vue.Vue;

public class Main {

	public static void main(String[] args) {
		
		AbstractGame party = new Game();
		AbstractControleur controleur = new GameControleur(party);
		Vue vue = new Vue(controleur);
		
		party.addObserver(vue);
		
		party.initializePlayers(); // How many players ? How many REAL and VIRTUAL Players ? Which Strategy ?
		
		party.initializeDeck(); //Create cards and add them to a packet named Deck
		
		party.initializeTrophies(); // From the deck, it defines the trophies
		
		
	}

}
