package fr.thomas_clement.modele;

import java.util.ArrayList;
import java.util.List;

import fr.thomas_clement.observer.Observable;
import fr.thomas_clement.observer.Observer;
import fr.thomas_clement.utt.*;

public abstract class AbstractGame implements Observable{
	
	private ArrayList<Observer> listeObserver = new ArrayList<Observer>();
	
	@Override
	public void addObserver(Observer obs) {
		this.listeObserver.add(obs);
		this.notifyReadyToPlay(false);
	}
	
	@Override
	public void notifyReadyToPlay(boolean readyToPlay) {
		
		for(Observer obs : this.listeObserver) {
			obs.notifyReadyToPlay(readyToPlay);
		}
	}
	
	@Override
	public void notifyStartPlateau(String path, int nbJoueurs, int deckSize) {
		for(Observer obs : this.listeObserver) {
			obs.updateStartPlateau(path, nbJoueurs, deckSize);
		}
	}
	
	@Override
	public void removeObserver() {
		this.listeObserver.clear();
	}
	

	public abstract void initializeDeck();

	public abstract void initializePlayers();

	public abstract void initializeTrophies();

	public abstract void playRounds();
	
	public abstract boolean isReadyToPlay();

	public abstract void setReadyToPlay(boolean readyToPlay);
	
	public abstract void initializePlayers(int nbJoueurs, int nbReels, int nbVirtuels, int nb1, int nb2);

}
