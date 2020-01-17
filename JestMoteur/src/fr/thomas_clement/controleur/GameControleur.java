package fr.thomas_clement.controleur;

import fr.thomas_clement.modele.AbstractGame;

/**
 * Type concret qui hérite de la classe AbstractControleur
 * Il s'agit du controleur du patron de conception MVC
 */
public class GameControleur extends AbstractControleur{
	
	/**
	 * Constructeur de la classe, créé un game controleur
	 * Appelle le constructeur de la AbstractControleur
	 * @param game
	 * 				Objet partie de Abstract game
	 */
	public GameControleur(AbstractGame game) {
		super(game);
	}

}
