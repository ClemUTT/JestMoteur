package fr.thomas_clement.observer;

/**
 * Interface Observer est permet d'observer des classe de type Observable
 */
public interface Observer {
	
	/**
	 * Permet de notifier la classe Observable lorsque la partie peut démarer ou non
	 * @param readyToPlay
	 * 				un boolean permettant de savoir si on peut commencer une partie ou non
	 */
	public void notifyReadyToPlay(boolean readyToPlay);
	
	/**
	 * Permet de mettre à jour la Vue en mettant la référence card, le nombre de joueurs et la taille du deck sur le plateau
	 * @param path
	 * 				chemin de la carte de référence
	 * @param nbJoueurs
	 * 				nombre de joueurs dans la partie en cours
	 * @param deckSize
	 * 				la taille du deck de cartes de la partie ne cours
	 */
	public void updateStartPlateau(String path, int nbJoueurs, int deckSize);

}
