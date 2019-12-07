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
	
	public void chooseOffers(List<Player> players) {
		this.strategy.chooseOffers(players);
	}

	public void makeOffers() {
		this.strategy.makeOffers(this);
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
