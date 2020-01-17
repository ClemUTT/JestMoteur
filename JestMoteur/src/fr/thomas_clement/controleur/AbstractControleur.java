package fr.thomas_clement.controleur;

import fr.thomas_clement.modele.AbstractGame;

/**
 * Type abstrait de la classe controleur qui permet de controler les actions de l'utilisateur sur la vue
 * Il s'agit du controleur du patron de conception MVC
 */
public abstract class AbstractControleur {
	
	/**
	 * Permet de faire le lien entre AbstractControleur et le modele
	 */
	protected AbstractGame game;
	
	/**
	 * Constructeur de la classe
	 * @param game
	 * 				L'attribut game
	 */
	public AbstractControleur(AbstractGame game) {
		this.game = game;
	}
	
	/**
	 * Méthode de capture de chaque action de l'utilisateur lors de l'initialisation de la partie qui va envoyé au modele
	 * ex : Nombre de joueurs réels, virtuels, niveau , etc...
	 * @param nbJoueurs
	 * 				L'attribut nombre de joueurs
	 * @param nbReels
	 * 				Nombre de joueurs réels
	 * @param nbVirtuels
	 * 				Nombre de joueurs virtuels
	 * @param nbNiv1
	 * 				Niveau easy
	 * @param nbNiv2
	 * 				Niveau hard
	 */
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
	
	/**
	 * Capture quand l'utilisateur clique sur jouer
	 * Appelle la méthode initializePlayers de l'attribut game
	 * @param nbJoueurs
	 * 				L'attribut nombre de joueurs
	 * @param nbReels
	 * 				Nombre de joueurs réels
	 * @param nbVirtuels
	 * 				Nombre de joueurs virtuels
	 * @param nbNiv1
	 * 				Niveau easy
	 * @param nbNiv2
	 * 				Niveau hard
	 */
	public void startInitializePlayers(int nbJoueurs, int nbReels, int nbVirtuels, int nbNiv1, int nbNiv2) {
		System.out.println("Initialisation des joueurs");
		// Appelle la méthode initializePlayers
		this.game.initializePlayers(nbJoueurs, nbReels, nbVirtuels, nbNiv1, nbNiv2);
	}
	
}
