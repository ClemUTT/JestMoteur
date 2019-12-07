package fr.thomas_clement.utt;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	
	protected Strategy strategy;
	protected String nickname;
	protected Jester jest;
	protected Packet hand;
	
	public Player(String nickname, Strategy strategy) {
		this.nickname = nickname;
		this.strategy = strategy;
		
		this.jest = new Jester(new ArrayList<Card>());
		this.hand = new Packet(new ArrayList<Card>());
	}
	
	public int makeOffers() {
		return this.strategy.makeOffers(this);
	}
	
	public Object[] chooseOffers(List<Player> players) {
		return this.strategy.chooseOffers(players, this);
	}

	
	public void sortPlayerHand() { // Sort the player hand in this way = 0; offer not hidden, 1; offer hidden
		Card cardHidden = null;
		Card cardNotHidden = null;
		
		
		for (int j = 0; j < this.getHand().getCards().size(); j++) { //Run the hand of the player in order to sort it out
			if(this.getHand().getCards().get(j).isFaceHidden()) { //If the card face is hidden
				cardHidden = this.getHand().getCards().get(j);
			} else {
				cardNotHidden = this.getHand().getCards().get(j);
			}
		} // End of the loop : sort out the player hand
		
		
		this.getHand().getCards().remove(0);
		this.getHand().getCards().remove(0);
		
		
		this.getHand().getCards().add(cardNotHidden); //The hand of each player is sorted out with 1st : cardNotHidden, 2nde : cardHidden
		this.getHand().getCards().add(cardHidden);
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	
	
	public Jester getJest() {
		return jest;
	}

	public void setJest(Jester jest) {
		this.jest = jest;
	}

	public Packet getHand() {
		return hand;
	}

	public void setHand(Packet hand) {
		this.hand = hand;
	}

	public String toString() {
		
		StringBuffer bf = new StringBuffer();
		
		bf.append(this.nickname);
		bf.append("; " + this.strategy.getClass().getSimpleName());
		
		return bf.toString();
	}
	
	

}
