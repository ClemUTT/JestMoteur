package fr.thomas_clement.modele;

import java.util.ArrayList;

import fr.thomas_clement.observer.Observable;
import fr.thomas_clement.observer.Observer;

/**
 * Il s'agit du modèle du patron MVC de mettre à jour les données de la Vue et de structurer le jeu
 * Cette classe contient les méthodes principales pour faire fonctionner l'application
 */
public abstract class AbstractGame implements Observable{
	
	/**
	 * Stocke les Observers dans un ArrayList
	 */
	private ArrayList<Observer> listeObserver = new ArrayList<Observer>();
	
	/**
     * Permet d'ajouter des observers
     * 
     * @param obs
     *            Observer
     */
	@Override
	public void addObserver(Observer obs) {
		this.listeObserver.add(obs);
		this.notifyReadyToPlay(false);
	}
	
	/**
	 * Méthode permettant de notifier la vue
	 * 
	 * @param readyToPlay
	 * 				boolean qui permet de savoir si la partie peut démarrer ou non
	 */
	@Override
	public void notifyReadyToPlay(boolean readyToPlay) {
		
		for(Observer obs : this.listeObserver) {
			obs.notifyReadyToPlay(readyToPlay);
		}
	}
	
	/**
	 * Méthode permettant de notifier la vue pour initialiser le plateau
	 * 
	 * @param path
	 * 				chemin de la carte de référence
	 * @param nbJoueurs
	 * 				nombre de joueurs dans la partie
	 * @param deckSize
	 * 				taille du deck
	 */
	@Override
	public void notifyStartPlateau(String path, int nbJoueurs, int deckSize) {
		for(Observer obs : this.listeObserver) {
			obs.updateStartPlateau(path, nbJoueurs, deckSize);
		}
	}
	
	/**
	 * Méthode permettant de supprimer les observers
	 */
	@Override
	public void removeObserver() {
		this.listeObserver.clear();
	}
	
	/**
	 * Méthode permettant d'initialiser le deck
	 */
	public abstract void initializeDeck();

	/**
	 * Méthode permettant d'initialiser les joueurs
	 */
	public abstract void initializePlayers();

	/**
	 * Méthode permettant d'initialiser les trophées
	 */
	public abstract void initializeTrophies();

	/**
	 * Méthode permettant de faire un round
	 */
	public abstract void playRounds();
	
	/**
	 * Méthode permettant savoir si on peut démarrer une partie ou non
	 * 
	 * @return boolean si on peut démarrer une partie ou non
	 */
	public abstract boolean isReadyToPlay();

	/**
	 * Méthode permettant de modifier le fait de si on peut démarrer une partie ou non
	 * 
	 * @param boolean si on peut démarrer une partie ou non
	 */
	public abstract void setReadyToPlay(boolean readyToPlay);
	
	/**
	 * Méthode permettant d'initiliaser les joueurs
	 * 
	 * @param nbJoueurs
	 * 			nombre de joueurs
	 * 
	 * @param nbReels
	 * 			nombre de joueurs réels
	 * 
	 * @param nbVirtuels
	 * 			nombre de joueurs virtuels
	 * 
	 * @param nb1
	 * 			nombre de niveau 1 
	 * 
	 * @param nb2
	 * 			nombre de niveau 2
	 */
	
	public abstract void initializePlayers(int nbJoueurs, int nbReels, int nbVirtuels, int nb1, int nb2);

}
