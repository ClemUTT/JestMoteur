package fr.thomas_clement.utt;

import java.util.ArrayList;

public class Jester extends Packet implements JesterVisitable{

	private int score;
	
	public Jester(ArrayList<Card> cards) {
		super(cards);
	}

	@Override
	public void accept(Game game) {
		game.calcul(this); //The visitor (Game) is able to visit the Jester (this)
	}

}
