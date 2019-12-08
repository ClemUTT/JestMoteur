package fr.thomas_clement.utt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	List<Player> players = new ArrayList<Player>();
	Packet deck;
	Packet stack = new Packet(new ArrayList<Card>());
	Packet trophies;
	Card reference_card;
	Player lastToPlay = null;
	Player choosing = null; // its turn
	List<Player> playersWhoHavePlayed = new ArrayList<Player>();
	
	int nbRounds = 1;
	
	
	public Game() {
		
	}
	
	public void playRounds() {
		// Check if there are still cards in the draw deck (this.deck)
		//while(this.deck.getCards().size() > 0) {
			
			System.out.println("\n---------------------------------\n");
			System.out.println("NOUS SOMMES AU ROUND " + this.nbRounds);
			System.out.println("\n---------------------------------\n");
			
			
			// Deal Cards to each player
			this.dealOffersToEachPlayer(this.getDeck(), 2);
			
			// The ace has a value of 1 for all the players who have an ace in its hand
			for (int i = 0; i < this.players.size(); i++) {
				if(this.players.get(i).getHand().getCards().get(0).getShape() == Shape.SPADES) {
					this.players.get(i).getHand().getCards().get(0).setValue(1);
				}
			}
			
			/*************************************************************************
			 ************************Make Offers************************************** 
			 *************************************************************************/
			
			// Ask to each player which offer they want to hide
			for (int i = 0; i < this.players.size(); i++) {
				
				System.out.println(this.players.get(i).getNickname() + ", choisissez l'offre que vous ne souhaitez PAS révéler (1 ou 2) parmi vos offres :");
				int answer = players.get(i).makeOffers();
				
				while(answer != 1 && answer != 2) {
					System.out.println("Veuillez choisir un nombre entre 1 et 2 : ");
					answer = players.get(i).makeOffers();
				}
				
				players.get(i).getHand().getCards().get(answer - 1).setFaceHidden(true);
				players.get(i).sortPlayerHand();
				
				System.out.println("-------------------------------------------");
			}
			
			
			
			System.out.println("Voici toutes les offres : \n");
			
			StringBuffer bf  = new StringBuffer();
			for (int i = 0; i < players.size(); i++) {
				bf.append("Nom : " + players.get(i).getNickname() + " \n");
				
				for (int j = 0; j < players.get(i).getHand().getCards().size(); j++) {
					bf.append("offre " + (j+1) + " : ");
					bf.append(players.get(i).getHand().getCards().get(j));
				}
				bf.append("\n --------------------------- \n");
			}
			System.out.println(bf.toString());
			
			
			/*************************************************************************
			 ************************Choose Offers************************************** 
			 *************************************************************************/
			
			//Determine who plays first
			this.whoPlaysFirst();
			
			// Ask to each player to choose an offer
			
			
			System.out.println("Celui qui joue en premier est : " + this.choosing.getNickname());
			
			for (int i = 0; i < players.size(); i++) { // There are as many choose as there are players
				List<Player> tabPlayersWhoCanBeChosen = tabPlayersWhoCanBeChosen(this.lastToPlay, this.choosing);
				
				if(this.playersWhoHavePlayed.contains(this.choosing)) {
					//If no one have 2 offers but itself has, so the player which is choosing an offer is itself
					this.choosing = tabPlayersWhoCanBeChosen.get(0);
				}
				System.out.println(this.choosing.getNickname() + ", choisissez un joueur parmi la liste suivante :");
				
				
				for (int j = 0; j < tabPlayersWhoCanBeChosen.size(); j++) {
					System.out.print(tabPlayersWhoCanBeChosen.get(j).getNickname() + ", ");
				}
				// tabPlayersWhoCanBeChosen(this.lastToPlay, this.choosing) ; return a tab with players that can be chosen
				
				//Asking...
				Object[] answer = this.choosing.chooseOffers(tabPlayersWhoCanBeChosen);
				
				while(!tabPlayersWhoCanBeChosen.contains(answer[0])) {
					System.out.println(answer[0] + "n'est pas un nom figurant dans la liste ci-dessus, réessayez :");
					answer = this.choosing.chooseOffers(tabPlayersWhoCanBeChosen(this.lastToPlay, this.choosing));
				}
				
				Player playerTaken = (Player) answer[0];
				int offerChosen = (int) answer[1]-1;
				
				System.out.println("\n" + this.choosing.getNickname() + " a choisi le joueur " + playerTaken.getNickname() + ", ainsi que l'offre " + answer[1]);
				System.out.println("\n --------------------------------------");
				
				playerTaken.getHand().addACardFromAPacketToAnotherPacket(offerChosen, this.choosing.getJest());
				
				
				//Say that a player has chosen for the entire round
				this.playersWhoHavePlayed.add(this.choosing);
				
				
				this.lastToPlay = this.choosing;
				this.choosing = playerTaken;
				
				
			} //End of the for loop that ask to choose
			

			/*************************************************************************
			 ************************Recover remaining offers*************************
			 *************************************************************************/
			
			//Add the remaining offers to the stack
			for (int j = 0; j < this.players.size(); j++) {
				this.players.get(j).getHand().getCards().get(0).setFaceHidden(false); // Change each face to false
				this.players.get(j).getHand().addACardFromAPacketToAnotherPacket(0, this.stack);
			}
			
			System.out.println("------------------------------");
			System.out.println("hand de chaque joueur : ");
			for (int i = 0; i < this.players.size(); i++) {
				System.out.println(this.players.get(i).getHand());
			}
			
			//Add to the stack a number of cards from the draw deck equal to the number of players
			for (int j = 0; j < players.size(); j++) {
				this.deck.addACardFromAPacketToAnotherPacket(0, this.stack);
			}
			
			System.out.println("------------------------------");
			System.out.println("stack :");
			System.out.println(this.stack);
			
			//Shuffle the stack
			this.stack.shuffleCards();
			
			//Deal 2 cards from the stack to each player
			this.dealOffersToEachPlayer(this.stack, 2);
			
			System.out.println("------------------------------");
			System.out.println("hand de chaque joueur : ");
			for (int i = 0; i < this.players.size(); i++) {
				System.out.println(this.players.get(i).getHand());
			}
			
			System.out.println("------------------------------");
			System.out.println("stack :");
			System.out.println(this.stack);
			
			System.out.println("------------------------------");
			System.out.println("deck :");
			System.out.println(this.deck);
			
			
			// Change round
			this.nbRounds += 1;
		//} //End while
	}
	
	public List<Player> tabPlayersWhoCanBeChosen(Player lastToPlay, Player choosing) {
		
		//lastToPlay = Player who just have chosen
		//choosing = Player who is going to choose (but who ?)
		List<Player> tabPlayers = new ArrayList<Player>();
		
		//Players that still have 2 offers
		for (int i = 0; i < this.players.size(); i++) {
			if(this.players.get(i).getHand().getCards().size() == 2) {
				tabPlayers.add(this.players.get(i));
			}
		}
		
		//Delete the player that is playing (if there is one, it's because it's the first player to play
		for (int i = 0; i < tabPlayers.size(); i++) {
			if(tabPlayers.get(i).equals(this.choosing)) {
				tabPlayers.remove(tabPlayers.get(i));
				break;
			}
		}
		
		//Delete the player that just have chosen
		for (int i = 0; i < tabPlayers.size(); i++) {
			if(tabPlayers.get(i).equals(this.lastToPlay)) {
				tabPlayers.remove(tabPlayers.get(i));
				break;
			}
		}
		
		return tabPlayers;
		
	}
	
	public void whoPlaysFirst() {
		
//		for (int i = 0; i < this.players.size(); i++) {
//			System.out.println(this.players.get(i).getHand());
//		}
		
		List<Player> playersSortedByValue = new ArrayList<Player>();
		List<Player> playersSortedByShape = new ArrayList<Player>();
		
		
		for (int i = 0; i < this.players.size(); i++) {
			
			if(playersSortedByValue.size() == 0) {
				//System.out.println("Si le tableau playersSortedByValue a une taille de 0");
				playersSortedByValue.add(this.players.get(i));
				//System.out.println("First to be added in the playerSortedByValue : " + playersSortedByValue);
			} else {
				//System.out.println("Else le tableau playersSortedByValue a une taille supérieure à 1");
				for (int j = 0; j < playersSortedByValue.size(); j++) {
					//System.out.println("entrée dans la boucle de playersSortedByValue ");
					if(this.players.get(i).getHand().getCards().get(0).getValue() == playersSortedByValue.get(j).getHand().getCards().get(0).getValue()) {
						//System.out.println("Si le player a une valeur de carte EGALE à celui qui est dans le tableau playersSortedByValue");
						//If the player in the sortedValue tab has the same value as the player in players tab, add it to the sortedValue tab
						playersSortedByValue.add(this.players.get(i));
						//System.out.println("Ensuite on ajoute à playerSortedByValue : " + playersSortedByValue);
						break;
					} else if(this.players.get(i).getHand().getCards().get(0).getValue() > playersSortedByValue.get(j).getHand().getCards().get(0).getValue()) {
						//System.out.println("Else if le player a une valeur de carte SUPERIEURE à celui qui est dans le tableau playersSortedByValue");
						playersSortedByValue.clear();
						playersSortedByValue.add(this.players.get(i));
						//System.out.println("Ensuite clear playerSortedByValue et on ajoute le meilleur: " + playersSortedByValue);
					}
				}
				
			} //End of the first else
			
		}
		
		System.out.println("Tab sorted by value : " + playersSortedByValue);
		
		if(playersSortedByValue.size() == 1) {
			// It's the only player so it's no use at all to compare the shape to itself
			//System.out.println("si le tableau playersSortedByValue a une taille de 1");
			playersSortedByShape.add(playersSortedByValue.get(0));
		} else {
			for (int i = 0; i < playersSortedByValue.size(); i++) {
				if(playersSortedByShape.size() == 0) {
					playersSortedByShape.add(playersSortedByValue.get(i));
				} else {
					
					for (int j = 0; j < playersSortedByShape.size(); j++) {
						if(playersSortedByValue.get(i).getHand().getCards().get(0).getShape().ordinal() > playersSortedByShape.get(j).getHand().getCards().get(0).getShape().ordinal()) {
							
							playersSortedByShape.clear();
							playersSortedByShape.add(playersSortedByValue.get(i));
							
						}
					}
					
					
				} // end of the second else
			} //end of the first for loop
		} // end of the first else
		
		System.out.println("Tab sorted by shape : " + playersSortedByShape + "\n");
		
		this.choosing = playersSortedByShape.get(0);
	}
	
	
	
	public void dealOffersToEachPlayer(Packet p, int nbCards) {
		for (int i = 0; i < players.size(); i++) {
			for (int j = 0; j < nbCards; j++) {
				p.addACardFromAPacketToAnotherPacket(0, players.get(i).getHand()); // Add a card (index 0) from the Packet p to the hand of the player i
			}
		}
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
			
			//Add 1 Card to the trophies
			this.deck.addACardFromAPacketToAnotherPacket(0, this.trophies);
			
		} else { //If there are 3 players
			
			//Add 2 random Cards to the trophies
			this.deck.addACardFromAPacketToAnotherPacket(0, this.trophies);
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
