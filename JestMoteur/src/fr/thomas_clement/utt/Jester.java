package fr.thomas_clement.utt;

import java.util.ArrayList;

public class Jester extends Packet implements Visitable{

	private int score;
	
	public Jester(ArrayList<Card> cards) {
		super(cards);
	}

	@Override
	public void accepterVisiteur(Visiteur v) {
		v.calculerScore(this);
	}
	
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int s) {
		this.score = s;
	}

}
