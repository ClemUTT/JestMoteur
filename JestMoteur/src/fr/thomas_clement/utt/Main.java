package fr.thomas_clement.utt;

import fr.thomas_clement.controleur.AbstractControleur;
import fr.thomas_clement.controleur.GameControleur;
import fr.thomas_clement.modele.AbstractGame;
import fr.thomas_clement.modele.Game;
import fr.thomas_clement.vue.Vue;

/**
 * Il s'agit de la classe principale qui fera fonctionner l'application
 *
 */
public class Main {

	/**
	 * Méthode qui permet de faire fonctionner l'application
	 * @param args
	 * 				arguments de la méthode
	 */
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
