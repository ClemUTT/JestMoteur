package fr.thomas_clement.utt;

public abstract class Player {
	
	protected Strategy strategy;
	protected String nickname; 
	
	public Player(String nickname, Strategy strategy) {
		this.nickname = nickname;
		this.strategy = strategy;
	}
	
	public void chooseOffers() {
		this.strategy.chooseOffers();
	}

	public void makeOffers() {
		this.strategy.makeOffers();
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
	
	public String toString() {
		
		StringBuffer bf = new StringBuffer();
		
		bf.append(this.nickname);
		bf.append("; " + this.strategy.getClass().getSimpleName());
		
		return bf.toString();
	}
	
	

}
