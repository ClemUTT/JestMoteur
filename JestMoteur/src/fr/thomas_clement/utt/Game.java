package fr.thomas_clement.utt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	List<Player> players = new ArrayList<Player>();
	Packet deck;
	Packet trophies;
	Card reference_card;
	
	
	public Game() {
		
	}
	
	public void initializeDeck() {

		//SPADES, CLUBS, DIAMONDS, HEARTS
		//HIGHTEST, LOWWEST, MAJORITY, BEST_JEST, BEST_JEST_NOJOKER, JOKER, REFERENCE_CARD;
		
		
		Card joker = new Card(0, false, false, Shape.JOKER, JestValue.BEST_JEST);
		Card referenceCard = new Card(-1, false, false, Shape.REFERENCE_CARD, JestValue.NONE);
		
		Card Spades5 = new Card(5, false, false, Shape.SPADES, JestValue.HIGHTEST); //Ace
		Card Clubs5 = new Card(5, false, false, Shape.CLUBS, JestValue.HIGHTEST); //Ace
		Card Diamonds5 = new Card(5, false, false, Shape.DIAMONDS, JestValue.MAJORITY_4); //Ace
		Card Hearts5 = new Card(5, false, false, Shape.HEARTS, JestValue.JOKER); //Ace
		
		Card Spades4 = new Card(4, false, false, Shape.SPADES, JestValue.LOWWEST);
		Card Clubs4 = new Card(4, false, false, Shape.CLUBS, JestValue.LOWWEST);
		Card Diamonds4 = new Card(4, false, false, Shape.DIAMONDS, JestValue.BEST_JEST_NOJOKER);
		Card Hearts4 = new Card(4, false, false, Shape.HEARTS, JestValue.JOKER);
		
		Card Spades3 = new Card(3, false, false, Shape.SPADES, JestValue.MAJORITY_2);
		Card Clubs3 = new Card(3, false, false, Shape.CLUBS, JestValue.HIGHTEST);
		Card Diamonds3 = new Card(3, false, false, Shape.DIAMONDS, JestValue.LOWWEST);
		Card Hearts3 = new Card(3, false, false, Shape.HEARTS, JestValue.JOKER);
		
		Card Spades2 = new Card(2, false, false, Shape.SPADES, JestValue.MAJORITY_3);
		Card Clubs2 = new Card(2, false, false, Shape.CLUBS, JestValue.LOWWEST);
		Card Diamonds2 = new Card(2, false, false, Shape.DIAMONDS, JestValue.HIGHTEST);
		Card Hearts2 = new Card(2, false, false, Shape.HEARTS, JestValue.JOKER);
		
		//We instance an ArrayList which will receive all the cards
		ArrayList<Card> cards = new ArrayList<>();
		
				
		//We add each Card instanced to the ArrayList
		cards.add(joker);
		this.reference_card = referenceCard;
		
		cards.add(Spades5);
		cards.add(Clubs5);
		cards.add(Diamonds5);
		cards.add(Hearts5);
		
		cards.add(Spades4);
		cards.add(Clubs4);
		cards.add(Diamonds4);
		cards.add(Hearts4);
		
		cards.add(Spades3);
		cards.add(Clubs3);
		cards.add(Diamonds3);
		cards.add(Hearts3);
		
		cards.add(Spades2);
		cards.add(Clubs2);
		cards.add(Diamonds2);
		cards.add(Hearts2);
		
		//The ArrayList of Cards is stocked in an instance of a Packet
		Packet deckCards = new Packet(cards);
		
		this.deck = deckCards;
	}
	
	public void initializeTrophies() {
		
		this.deck.shuffleCards(); //We shuffle the deck
		this.trophies = new Packet(new ArrayList<Card>()); //We set the variable as a new Packet
		
		if(this.players.size() == 4) { //If there are 4 players
			
			//Add 2 Cards to the trophies
			this.deck.addACardFromAPacketToAnotherPacket(0, this.trophies);
			
			this.deck.addACardFromAPacketToAnotherPacket(1, this.trophies);
			
		} else { //If there are 3 players
			
			//Add 1 random Card to the trophies
			this.deck.addACardFromAPacketToAnotherPacket(0, this.trophies);
		}
		
	}
	
	
	public void initializePlayers() {
		Scanner sc = new Scanner(System.in);
		int nbPlayers = 0;
		int nbRealPlayers = 0;
		
		//Ask the amount of players wanted
		System.out.println("Saisissez le nombre de joueurs souhaité pour cette partie (3 ou 4)");
		
		nbPlayers = sc.nextInt();
		while(nbPlayers != 3 && nbPlayers != 4) {
			System.out.println("Vous devez choisir 3 ou 4 :");
			nbPlayers = sc.nextInt();
		}
		
		//Ask the amount of REAL players wanted
		System.out.println("Saisissez le nombre de joueurs RÉELS parmis ces " + nbPlayers + " joueurs :");
		
		nbRealPlayers = sc.nextInt();
		while(nbRealPlayers < 0 || nbRealPlayers > nbPlayers) {
			System.out.println("Vous devez choisir un nombre compris entre 1 et " + nbPlayers + " :");
			nbRealPlayers = sc.nextInt();
		}
		
		if(nbPlayers != nbRealPlayers) {
			System.out.println("Il y a donc " + nbRealPlayers + " joueurs réels, et " + (nbPlayers - nbRealPlayers) + " joueurs Virtuels.");
		}
		
		//Ask the level of difficulty for each virtual players
		int requests = nbPlayers - nbRealPlayers;
		
		for (int i = 1; i <= requests; i++) {
				System.out.println("Choisissez un niveau de difficulté pour le joueur virtuel numéro " + i + "(easy : 1, hard : 2)");
				int answer = sc.nextInt();
				
				while(answer != 1 && answer != 2) {
					System.out.println("Vous devez choisir 1 ou 2 :");
					answer = sc.nextInt();
				}
				
				if(answer == 1) {
					this.players.add(new VirtualPlayer(new VirtualPlayerRandom()));
				} else if (answer == 2) {
					this.players.add(new VirtualPlayer(new VirtualPlayerDifficult()));
				}
			
		}
		
		if(nbRealPlayers > 0) {
			for (int i = 1; i <= nbRealPlayers; i++) {
				System.out.println("Définissez un nom pour le joueur réel numéro " + i + " : ");
				String answer = sc.next();
				
				while(answer.length() > 20) {
					System.out.println("Choisissez un nom moins long !");
					answer = sc.next();
				}
				
				this.players.add(new RealPlayer(answer));
			}
			
		}
		
		//System.out.println(this.players);
	
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Packet getDeck() {
		return deck;
	}

	public Packet getTrophies() {
		return trophies;
	}
	
	
	
	
}
