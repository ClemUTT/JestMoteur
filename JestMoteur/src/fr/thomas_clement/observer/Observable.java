package fr.thomas_clement.observer;

import java.util.List;

import fr.thomas_clement.utt.*;

public interface Observable {
	
	public void addObserver(Observer obs);
	
	public void removeObserver();
	
	public void notifyReadyToPlay(boolean readyToPlay); 
	
	public void notifyStartPlateau(String path, int nbJoueurs, int deckSize);

}
