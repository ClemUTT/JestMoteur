package fr.thomas_clement.controleur;

import fr.thomas_clement.modele.AbstractGame;

public class GameControleur extends AbstractControleur{

	public GameControleur(AbstractGame game) {
		super(game);
	}

	@Override
	public void griseRadioButton(String natureJoueur) {
		
		this.game.calculRadioButtonsStart(natureJoueur);
		
	}

}
