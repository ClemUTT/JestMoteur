package fr.thomas_clement.controleur;

import fr.thomas_clement.modele.AbstractGame;

public abstract class AbstractControleur {

	protected AbstractGame game;
	
	public AbstractControleur(AbstractGame game) {
		this.game = game;
	}

	
	public void jouer() {
		
	}
	
	public void controlStart(int nbJoueurs, int nbReels, int nbVirtuels, int nbNiv1, int nbNiv2) {
		System.out.println("--------------------------------------");
		System.out.println("nbJoueurs : " + nbJoueurs);
		System.out.println("nbReels : " + nbReels);
		System.out.println("nbVirtuels : " + nbVirtuels);
		System.out.println("nbNiv1 : " + nbNiv1);
		System.out.println("nbNiv2 : " + nbNiv2);
		
		// Si tout est bon on rend le bouton jouer enable.
		if((nbReels + nbVirtuels == nbJoueurs) && (nbNiv1 + nbNiv2 == nbVirtuels)) {
			System.out.println("good");
			this.game.notifyReadyToPlay(true);
		} else {
			System.out.println("bad");
		// Si tout n'est pas bon on rend le bouton jouer disable
			this.game.notifyReadyToPlay(false);
		}
		
	}
	
	public void startInitializePlayers(int nbJoueurs, int nbReels, int nbVirtuels, int nbNiv1, int nbNiv2) {
		System.out.println("Initialisation des joueurs");
		// Appelle la méthode initializePlayers
		this.game.initializePlayers(nbJoueurs, nbReels, nbVirtuels, nbNiv1, nbNiv2);
	}
	
}
