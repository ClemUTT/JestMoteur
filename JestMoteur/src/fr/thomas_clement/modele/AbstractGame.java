package fr.thomas_clement.modele;

import java.util.ArrayList;

import fr.thomas_clement.observer.Observable;
import fr.thomas_clement.observer.Observer;

public abstract class AbstractGame implements Observable{
	
	private ArrayList<Observer> listeObserver = new ArrayList<Observer>();
	
	@Override
	public void addObserver(Observer obs) {
		this.listeObserver.add(obs);
		this.notifyStart(0, 0, 0, 0, false);
	}
	
	@Override
	public void notifyStart(int nbReels, int nbVirtuels, int nbVirtuelSelected, int nbReelSelected, boolean readyToPlay) {
		
		for(Observer obs : this.listeObserver) {
			obs.updateStart(nbReels, nbVirtuels, nbVirtuelSelected, nbReelSelected, readyToPlay);
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
	
	public abstract void calculRadioButtonsStart(String natureJoueur);
	
	public abstract void setNbPlayers(int nb);
	
	public abstract void setNbRealPlayers(int nb);
	
	public abstract void setNbVirtualPlayers(int nb);
	
	public abstract void setNiv(int niv1, int niv2);
	
	public abstract int getNbVirtualPlayers();
	
	public abstract boolean isReadyToPlay();

	public abstract void setReadyToPlay(boolean readyToPlay);

}
