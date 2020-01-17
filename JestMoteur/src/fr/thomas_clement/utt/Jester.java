package fr.thomas_clement.utt;

import java.util.ArrayList;

/**
 * Classe du m�me type que packet qui stock les cartes qui ont �t� choisi par le joueur
 * Elle h�rite de la classe Packet et impl�mente l'interface Visitable
 */
public class Jester extends Packet implements Visitable{
	
	/**
	 * Valeur du score (entier)
	 */
	private int score;
	
	/**
	 * Constructeur de la classe
	 * Appelle le constructeur de la classe dont elle h�rite
	 * @param cards
	 * 				Liste de carte de type Carte
	 */
	public Jester(ArrayList<Card> cards) {
		super(cards);
	}
	
	/**
	 * Red�finition de la m�thode permettant de calculer le score du joueur en question
	 * @param v
	 * 				Un objet de Visiteur
	 */
	@Override
	public void accepterVisiteur(Visiteur v) {
		v.calculerScore(this);
	}
	
	/**
	 * Retourner la valeur
	 * @return score du Jest
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Modifier le score du jest en question
	 * @param s
	 * 				score
	 */
	public void setScore(int s) {
		this.score = s;
	}

}
