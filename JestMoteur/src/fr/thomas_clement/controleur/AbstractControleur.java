package fr.thomas_clement.controleur;

import fr.thomas_clement.modele.AbstractGame;

public abstract class AbstractControleur {

	protected AbstractGame game;
	
	public AbstractControleur(AbstractGame game) {
		this.game = game;
	}
	
	public void setNombreRJ() {
		
		griseRadioButton();
	}
	
	public void setNombreVJ() {
		
		griseRadioButton();
	}
	
	public void jouer() {
		
	}
	
	abstract public void griseRadioButton();
	
}
