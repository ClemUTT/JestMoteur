package fr.thomas_clement.observer;

import java.util.List;

import fr.thomas_clement.utt.*;

public interface Observer {
	
	public void notifyReadyToPlay(boolean readyToPlay);
	
	public void updateStartPlateau(String path, int nbJoueurs, int deckSize);

}
