package fr.thomas_clement.utt;

import java.util.ArrayList;
import java.util.List;

/**
 * Type abstrait joueur
 */
public abstract class Player {
	
	/**
	 * Stratégie du joueur
	 * @see Strategy
	 */
	protected Strategy strategy;
	
	/**
	 * Pseudo du joueur
	 */
	protected String nickname;
	
	/**
	 * Jest du joueur
	 * @see Jester
	 */
	protected Jester jest;
	
	/**
	 * Les deux cartes du joueur
	 * @see Packet
	 */
	protected Packet hand;
	
	/**
	 * Constructeur de la classe
	 * 
	 * @param nickname
	 * 				pseudo
	 * @param strategy
	 * 				stratégie
	 */
	public Player(String nickname, Strategy strategy) {
		this.nickname = nickname;
		this.strategy = strategy;
		
		this.jest = new Jester(new ArrayList<Card>());
		this.hand = new Packet(new ArrayList<Card>());
	}
	
	/**
	 * Retourne un numéro d'offre
	 * @return le numéro de l'offre choisie par l'attribut stratégie
	 */
	public int makeOffers() {
		return this.strategy.makeOffers(this);
	}
	
	/**
	 * Choisi un joueur et son offre parmi la liste des joueurs dont il peut choisir l'offre
	 * @param players
	 * 				Liste des joueurs 
	 * @return tableau d'objet de taille 2 (en 1er : la liste des joueurs dont le joueur peut choisir, en 2eme le joueur qui est en train de choisir)
	 */
	public Object[] chooseOffers(List<Player> players) {
		return this.strategy.chooseOffers(players, this);
	}

	/**
	 * Trie la main du joueur de cette manière : 0 = offre pas caché , 1 = offre cachée
	 */
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
	
	/**
	 * Retourner la valeur
	 * @return pseudo
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Modifier le pseudo du joueur
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * Retourner la valeur
	 * @return startégie
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	/**
	 * Modifier la stratégie du joueur
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * Retourner la valeur
	 * @return jest du joueur
	 */
	public Jester getJest() {
		return jest;
	}
	
	/**
	 * Modifier le jest du joueur
	 */
	public void setJest(Jester jest) {
		this.jest = jest;
	}
	
	/**
	 * Retourner la valeur
	 * @return main du joueur
	 */
	public Packet getHand() {
		return hand;
	}
	
	/**
	 * Modifier la main du joueur
	 */
	public void setHand(Packet hand) {
		this.hand = hand;
	}

	/**
	 * Décrit l'objet de la classe sous forme d'une chaine de caracteres
	 * @return Chaine de caractere représentant le pseudo du joueur avec sa strategie
	 */
	public String toString() {
		
		StringBuffer bf = new StringBuffer();
		
		bf.append(this.nickname);
		bf.append("; " + this.strategy.getClass().getSimpleName());
		
		return bf.toString();
	}
	
	

}
