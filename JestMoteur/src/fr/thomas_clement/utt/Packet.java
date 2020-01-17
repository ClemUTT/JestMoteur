package fr.thomas_clement.utt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Type packet contenant une liste de cartes
 */
public class Packet {
	
	/**
	 * Liste de carte ArrayList de type Carte
	 * @see List
	 */
	private List<Card> cards =  new ArrayList<Card>() ;
	
	/**
	 * Constructeur de la classe
	 * @param cards
	 * 				Liste de carte de type Carte
	 */
	public Packet(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	/**
	 * Mélange aléatoirement la liste des cartes du paquet en question
	 */
	public void shuffleCards() {
		Collections.shuffle(this.cards);
	}
	
	/**
	 * Ajoute une carte de ce paquet vers un autre paquet
	 * @param indexCard
	 * 				index de la carte dans le paquet
	 * @param newPacket
	 * 				nouveau packet de type Paquet
	 */
	public void addACardFromAPacketToAnotherPacket(int indexCard, Packet newPacket) { // Avoid to implement add() and remove() every time when you want to move a card to another Packet
		Packet currentPacket = this;
		
		Card c = this.cards.get(indexCard); //Store the card you want to move in a variable
		newPacket.addCard(c); // Add this Card c to the Packet p
		currentPacket.removeCard(c); // Remove this Card c to the old Packet this
	}
	
	/**
	 * Ajoute une carte de type Carte au paquet
	 * 
	 * @param card
	 * 			Card à ajouter
	 */
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	/**
	 * Retire une carte de type Carte au paquet
	 * 
	 * @param card
	 * 			Card à supprimer
	 */
	public void removeCard(Card card) {
		this.cards.remove(card);
	}
	/**
	 * Récupérer la valeur
	 * @return Liste de cartes de type Carte du paquet
	 * @see List
	 */
	public List<Card> getCards() {
		return cards;
	}
	
	/**
	 * Décrit l'objet de la classe sous forme d'une chaine de caracteres
	 * @return toString() des cartes du paquets et la taille du paquet
	 */
	public String toString() {
		return this.cards.toString() + " size : " + this.cards.size();
	}
	
	
	
}
