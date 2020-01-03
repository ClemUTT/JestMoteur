package fr.thomas_clement.observer;

public interface Observable {
	
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyStart(int nbReels, int nbVirtuels); 

}
