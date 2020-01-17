package fr.thomas_clement.observer;

/**
 * Interface Observable est permet d'être observée par des classe de type Observer
 */
public interface Observable {
	
	/**
	 * Permet d'ajouter des Observers
	 * @param obs
	 * 				Un observer de type Observer
	 */
	public void addObserver(Observer obs);
	
	/**
	 * Permet de supprimer des Observers
	 */
	public void removeObserver();
	
	/**
	 * Permet de notifier les Observers de si la partie peut être démarée ou non
	 * @param readyToPlay
	 * 				boolean qui permet de savoir si on peut démarrer une partie ou non
	 */
	public void notifyReadyToPlay(boolean readyToPlay); 
	
	/**
	 * Permet de notifier les Observers pour l'initialisation du plateau de la aprtie
	 * @param path
	 * 				chemin de la carte de référence
	 * @param nbJoueurs
	 * 				nombre de joueurs dans la partie en cours
	 * @param deckSize
	 * 				la taille du deck de cartes de la partie ne cours
	 */
	public void notifyStartPlateau(String path, int nbJoueurs, int deckSize);

}
