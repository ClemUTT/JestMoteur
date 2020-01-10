package fr.thomas_clement.observer;

public interface Observer {
	
	public void notifyReadyToPlay(boolean readyToPlay);
	
	public void updateStartPlateau(String path, int nbJoueurs, int deckSize);

}
