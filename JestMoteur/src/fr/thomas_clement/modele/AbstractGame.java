package fr.thomas_clement.modele;

import java.util.ArrayList;

import fr.thomas_clement.observer.Observable;
import fr.thomas_clement.observer.Observer;

public abstract class AbstractGame implements Observable{
	
	private ArrayList<Observer> listeObserver = new ArrayList<Observer>();
	
	@Override
	public void addObserver(Observer obs) {
		this.listeObserver.add(obs);
	}
	
	@Override
	public void notifyStart(int nbReels, int nbVirtuels) {
		
		for(Observer obs : this.listeObserver) {
			obs.updateStart(nbReels, nbVirtuels);
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

}
