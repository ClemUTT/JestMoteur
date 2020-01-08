package fr.thomas_clement.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.thomas_clement.utt.Card;
import fr.thomas_clement.utt.JestValue;
import fr.thomas_clement.utt.Jester;
import fr.thomas_clement.utt.Packet;
import fr.thomas_clement.utt.Player;
import fr.thomas_clement.utt.RealPlayer;
import fr.thomas_clement.utt.Shape;
import fr.thomas_clement.utt.VirtualPlayer;
import fr.thomas_clement.utt.VirtualPlayerDifficult;
import fr.thomas_clement.utt.VirtualPlayerRandom;
import fr.thomas_clement.utt.Visiteur;

import java.util.Scanner;

public class Game extends AbstractGame implements Visiteur {
	
	List<Player> players = new ArrayList<Player>();
	Packet deck;
	Packet stack = new Packet(new ArrayList<Card>());
	Packet trophies;
	Card reference_card;
	Player lastToPlay = null;
	Player choosing = null; // its turn
	List<Player> playersWhoHavePlayed = new ArrayList<Player>();
	
	private int nbRounds = 1;
	private boolean readyToPlay = false;
	private boolean nextPlayer = false;
	
	
	public boolean isReadyToPlay() {
		return readyToPlay;
	}

	public void setReadyToPlay(boolean readyToPlay) {
		this.readyToPlay = readyToPlay;
	}

	public Game() {
		
	}
	
	public void initializePlayers(int nbJoueurs, int nbReels, int nbVirtuels, int nb1, int nb2) {
		this.players.clear();
		if(nbVirtuels > 0) {
				for (int j = 0; j < nb1; j++) {
					this.players.add(new VirtualPlayer(new VirtualPlayerRandom()));
				}
				for (int j = 0; j < nb2; j++) {
					this.players.add(new VirtualPlayer(new VirtualPlayerDifficult()));
				}
		}
		
		if(nbReels > 0) {
			for (int i = 0; i < nbReels; i++) {
				this.players.add(new RealPlayer("jr"+(i+1)));
			}
		}
		System.out.println(this.players);
		this.initializeDeck();
	}
	
	public void calculerScore(Jester jest) {
		int score = 0;
		
		List<Card> tabHearts = new ArrayList<>();
		List<Card> tabAce = new ArrayList<>();
		List<Card> tabClubs = new ArrayList<>();
		List<Card> tabDiamonds = new ArrayList<>();
		List<Card> tabSpades = new ArrayList<>();
		boolean hasJoker = false;
		
		
		//Add all cards in their tab
		for (int i = 0; i < jest.getCards().size(); i++) {
			
			if(jest.getCards().get(i).getShape().equals(Shape.HEARTS)) {
				tabHearts.add(jest.getCards().get(i));
			} else if(jest.getCards().get(i).getShape().equals(Shape.CLUBS)) {
				tabClubs.add(jest.getCards().get(i));
			} else if(jest.getCards().get(i).getShape().equals(Shape.DIAMONDS)) {
				tabDiamonds.add(jest.getCards().get(i));
			} else if(jest.getCards().get(i).getShape().equals(Shape.SPADES)) {
				tabSpades.add(jest.getCards().get(i));
			}
			
			if(jest.getCards().get(i).getValue() == 5) {
				tabAce.add(jest.getCards().get(i));
			}
			
			if(jest.getCards().get(i).getShape().equals(Shape.JOKER)) {
				System.out.println("Il a le joker !! ");
				hasJoker = true;
			}
		}
		
		// If there is an ace which has the only suit of all the other cards
		// the face value becomes a 5
		for (int i = 0; i < tabAce.size(); i++) {
			for (int j = 0; j < jest.getCards().size(); j++) {
				if(tabAce.get(i).getShape().equals(jest.getCards().get(j).getShape()) && jest.getCards().get(j).getValue() != 5) {
					tabAce.get(i).setValue(1);
					System.out.println("un ace a value de 1");
					break;
				} else if(j == jest.getCards().size()-1) {
					tabAce.get(i).setValue(5);
					System.out.println("un ace a value de 5");
					break;
				}
			}
		}
		
		// Clubs always increase the jest score by its face value
		if(tabClubs.size() > 0) {
			for (int i = 0; i < tabClubs.size(); i++) {
				score += tabClubs.get(i).getValue();
			}
			System.out.println("clubs " + score);
		}
		
		// Spades always increase the jest score by its face value
		if(tabSpades.size() > 0) {
			for (int i = 0; i < tabSpades.size(); i++) {
				score += tabSpades.get(i).getValue();
			}
			System.out.println("spades " + score);
		}
		
		// Diamonds always decrease the jest score by its face value
		if(tabDiamonds.size() > 0) {
			for (int i = 0; i < tabDiamonds.size(); i++) {
				score -= tabDiamonds.get(i).getValue();
			}
			System.out.println("diamonds " + score);
		}
		
		// If there is the joker and no hearts
		if(hasJoker && tabHearts.size() == 0) {
			//the jest score increases by 4
			score += 4;
			System.out.println("joker and 0 hearts " + score);
		}
		
		
		// If there is the joker and 1, 2 or 3 hearts
		if(hasJoker && (tabHearts.size() == 1 || tabHearts.size() == 2 || tabHearts.size() == 3)) {
			// the jest score decreases by the face value of each heart
			for (int i = 0; i < tabHearts.size(); i++) {
				score -= tabHearts.get(i).getValue();
			}
			System.out.println("joker and 1,2,3 hearts " + score);
		}
		
		// If there is the joker and the 4 hearts
		if(hasJoker && tabHearts.size() == 4) {
			// the jest score increases by the face value of each heart
			for (int i = 0; i < tabHearts.size(); i++) {
				score += tabHearts.get(i).getValue();
			}
			System.out.println("joker and 4 hearts " + score);
		}
		
		
		// If there are a Spade and a Club with the same face value
		if(tabSpades.size() > 0 && tabClubs.size() > 0) {
			for (int i = 0; i < tabSpades.size(); i++) {
				for (int j = 0; j < tabClubs.size(); j++) {
					if(tabSpades.get(i).getValue() == tabClubs.get(j).getValue()) {
						score += 2;
					}
				}
			}
			System.out.println("spade and club " + score);
		}
		
		jest.setScore(score);
	}
	
	public void playRounds() {
		
		//For each round, the the value of an ace equals to 1
		for (int i = 0; i < this.deck.getCards().size(); i++) {
			if(this.deck.getCards().get(i).getValue() == 5) {
				this.deck.getCards().get(i).setValue(1);
			}
		}
		
		// Check if there are still cards in the draw deck (this.deck)
		while(this.deck.getCards().size() > 0 ) {
			
			System.out.println("\n---------------------------------\n");
			System.out.println("NOUS SOMMES AU ROUND " + this.nbRounds);
			System.out.println("\n---------------------------------\n");
			System.out.println("Deck : \n" + this.deck);
			
			
			// Deal Cards to each player if it's the first round
			if(this.nbRounds == 1) {
				
				this.dealOffersToEachPlayer(this.getDeck(), 2);
				//this.notifyStartPlateau(this.reference_card.getPath(), this.players.size(), this.deck.getCards().size());
				
			} else {
				
				
				//Add to the stack a number of cards from the draw deck equal to the number of players
				for (int j = 0; j < players.size(); j++) {
					this.deck.addACardFromAPacketToAnotherPacket(0, this.stack);
				}
				
				//Shuffle the stack
				this.stack.shuffleCards();
				
				//Deal 2 cards from the stack to each player
				this.dealOffersToEachPlayer(this.stack, 2);
			}
				
			
		
//			// The ace has a value of 1 for all the players who have an ace in its hand
//			for (int i = 0; i < this.players.size(); i++) {
//				if(this.players.get(i).getHand().getCards().get(0).getShape() == Shape.SPADES) {
//					this.players.get(i).getHand().getCards().get(0).setValue(1);
//				}
//			}
			
			/*************************************************************************
			 ************************Make Offers************************************** 
			 *************************************************************************/
			
			// Ask to each player which offer they want to hide
			for (int i = 0; i < this.players.size(); i++) {
				
				System.out.println(this.players.get(i).getNickname() + ", choisissez l'offre que vous ne souhaitez PAS r�v�ler (1 ou 2) parmi vos offres :");
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
					System.out.println(answer[0] + "n'est pas un nom figurant dans la liste ci-dessus, r�essayez :");
					answer = this.choosing.chooseOffers(tabPlayersWhoCanBeChosen(this.lastToPlay, this.choosing));
				}
				
				Player playerTaken = (Player) answer[0];
				int offerChosen = (int) answer[1]-1;
				
				System.out.println("\n" + this.choosing.getNickname() + " a choisi le joueur " + playerTaken.getNickname() + ", ainsi que l'offre " + answer[1]);
				System.out.println("\n --------------------------------------");
				
				playerTaken.getHand().addACardFromAPacketToAnotherPacket(offerChosen, this.choosing.getJest());
				this.choosing.getJest().getCards().get(this.choosing.getJest().getCards().size()-1).setFaceHidden(false); //Switch the faceHidden offer to not hidden
				
				
				//Say that a player has chosen for the entire round
				this.playersWhoHavePlayed.add(this.choosing);
				
				
				this.lastToPlay = this.choosing;
				this.choosing = playerTaken;
				
				
			} //End of the for loop that ask to choose
			
			
				
				/*************************************************************************
				 ************************END OF A ROUND*************************
				 *************************************************************************/
				
			System.out.println("****** FIN DU ROUND *******");
			System.out.println("------------------------------");
			
			//For the final round
			if(this.deck.getCards().size() == 0) {
				//Add each offer to the jest of the owner of the offer
				for (int i = 0; i < this.players.size(); i++) {
					this.players.get(i).getHand().addACardFromAPacketToAnotherPacket(0, this.players.get(i).getJest());
				}
			} else {
				//Add the remaining offers to the stack
				for (int j = 0; j < this.players.size(); j++) {
					this.players.get(j).getHand().getCards().get(0).setFaceHidden(false); // Change each face to false
					this.players.get(j).getHand().addACardFromAPacketToAnotherPacket(0, this.stack);
				}
			}
			

			/*************************************************************************
			 ************************RE - Initialization************************************** 
			 *************************************************************************/
			
			this.lastToPlay = null;
			this.choosing = null;
			this.playersWhoHavePlayed = new ArrayList<Player>(); // At the end of a round, no one has played
			
			// Change round
			this.nbRounds += 1;
		} //End while
		
		this.endOfTheGame();
	}
	
	private void endOfTheGame() {
		
		System.out.println("----------------------------");
		System.out.println("-------FIN DE LA PARTIE-----");
		System.out.println("----------------------------");
		
		//At the end of the game, the the value of an ace equals to 5, and each jest card is not hidden anymore
			for (int i = 0; i < this.players.size(); i++) {
				for (int j = 0; j < this.players.get(i).getJest().getCards().size(); j++) {
					this.players.get(i).getJest().getCards().get(j).setFaceHidden(false);
					if(this.players.get(i).getJest().getCards().get(j).getValue() == 1) {
						this.players.get(i).getJest().getCards().get(j).setValue(5);
					}
				}
				
			}
		
		System.out.println("Deck : \n" + this.deck);
		
		System.out.println("Stack : \n" + this.stack);
		
		System.out.println("Stack : \n" + this.trophies);
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Jest de " + players.get(i).getNickname() + " : ");
			System.out.println(players.get(i).getJest());
			System.out.println("-------------------");
		}
		
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Hand de " + players.get(i).getNickname() + " : ");
			System.out.println(players.get(i).getHand());
		}
		
		System.out.println("----- Troph�es ----");
		System.out.println(this.trophies);
	
		
		/*************************************************************************
		 ************************WHO GETS THE TROPHIE(S)**************************
		 *************************************************************************/

		this.calculateTrophies();
		
		/*************************************************************************
		 ***************************WHO IS THE WINNER*****************************
		 *************************************************************************/
		
		
		for (int i = 0; i <this.players.size(); i++) {
			System.out.println("--*-*-*-*-*-*-*-*-*-*-*-*-*-*");
			System.out.println("Calcul du score de " + this.players.get(i));
			System.out.println(this.players.get(i).getJest());
			
			this.players.get(i).getJest().accepterVisiteur(this);
			
			System.out.println("le score de " + this.players.get(i).getNickname() + " est de " + this.players.get(i).getJest().getScore());
		}
		
		Player winner = null;
		for (int i = 0; i < this.players.size(); i++) {
			System.out.println(this.players.get(i).getNickname() + " : score de " + this.players.get(i).getJest().getScore());
			if(winner == null) {
				winner = this.players.get(i);
			} else if(this.players.get(i).getJest().getScore() > winner.getJest().getScore()) {
				winner = this.players.get(i);
			}
		}
		
		System.out.println("******************");
		System.out.println("Le gagnant est " + winner.getNickname() + ", avec un score de " + winner.getJest().getScore());
		System.out.println("******************");
		
	}
	
	public void calculateTrophies() {
		List<Player> winners = new ArrayList<>();
		
		for (int i = 0; i < this.trophies.getCards().size(); i++) {
			switch(this.trophies.getCards().get(i).getJestValue()) {
				
			case HIGHTEST_HEARTS: winners.add(calculateHightest(Shape.HEARTS));
				break;
			case HIGHTEST_DIAMONDS: winners.add(calculateHightest(Shape.DIAMONDS));
				break;
			case HIGHTEST_CLUBS: winners.add(calculateHightest(Shape.CLUBS));
				break;
			case HIGHTEST_SPADES: winners.add(calculateHightest(Shape.SPADES));
				break;
			case LOWEST_HEARTS: winners.add(calculateLowest(Shape.HEARTS));
				break;
			case LOWEST_DIAMONDS: winners.add(calculateLowest(Shape.DIAMONDS));
				break;
			case LOWEST_CLUBS: winners.add(calculateLowest(Shape.CLUBS));
				break;
			case LOWEST_SPADES: winners.add(calculateLowest(Shape.SPADES));
				break;
			case MAJORITY_4: winners.add(calculateMajority(4));
				break;
			case MAJORITY_3: winners.add(calculateMajority(3));
				break;
			case MAJORITY_2: winners.add(calculateMajority(2));
				break;
			case BEST_JEST: winners.add(calculateBestJest(true));
				break;
			case BEST_JEST_NOJOKER: winners.add(calculateBestJest(false));
				break;
			case JOKER: winners.add(calculateJoker());
			default:
				break;
			
			}
			
		}
		
		for (int i = 0; i < winners.size(); i++) {
			this.trophies.addACardFromAPacketToAnotherPacket(0, winners.get(i).getJest());
		}
		
//		for (int j = 0; j < players.size(); j++) {
//			System.out.println(players.get(j).getNickname());
//			System.out.println(players.get(j).getJest());
//		}
	}
	
	public Player calculateHightest(Shape s) {
		//System.out.println("entr�e dans la m�thode calculateHightest pour la forme " + s.name());
		Player highestValue = null;
		Card card = null;
		
		for (int i = 0; i < this.players.size(); i++) { //Run each player
			
			for (int k = 0; k < this.players.get(i).getJest().getCards().size(); k++) { //Run each player card jest
				
				if(highestValue == null && this.players.get(i).getJest().getCards().get(k).getShape().equals(s)) {
					//If there is no player in the variable highestValue and the current player jest card has the same shape of the condition, then change the variables below
					highestValue = this.players.get(i);
					card = this.players.get(i).getJest().getCards().get(k); //Store its jest card
				}
				
				if(this.players.get(i).getJest().getCards().get(k).getShape().equals(s)) {
					if(this.players.get(i).getJest().getCards().get(k).getValue() > card.getValue()) {
						highestValue = this.players.get(i);
						card = this.players.get(i).getJest().getCards().get(k); //Store its jest card
					}
				} //End if equals to s
			} //End run each player card
		} //End run each player
		
		System.out.println("Le gagnant de la condition HIGHTEST_" + s.name().toUpperCase() + " est " + highestValue.getNickname());
		return highestValue;
//		for (int i = 0; i < this.trophies.getCards().size(); i++) {
//			if(s.equals(Shape.CLUBS)) {
//				if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.HIGHTEST_CLUBS)) {
//					this.trophies.addACardFromAPacketToAnotherPacket(i, highestValue.getJest());
//				}
//			} else if(s.equals(Shape.SPADES)) {
//				if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.HIGHTEST_SPADES)) {
//					this.trophies.addACardFromAPacketToAnotherPacket(i, highestValue.getJest());
//				}
//			} else if(s.equals(Shape.HEARTS)) {
//				if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.HIGHTEST_HEARTS)) {
//					this.trophies.addACardFromAPacketToAnotherPacket(i, highestValue.getJest());
//				}
//			} else if(s.equals(Shape.DIAMONDS)) {
//				if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.HIGHTEST_DIAMONDS)) {
//					this.trophies.addACardFromAPacketToAnotherPacket(i, highestValue.getJest());
//				}
//			}
//			
//		}
	}
	
	public Player calculateLowest(Shape s) {
		//System.out.println("entr�e dans la m�thode calculateLowest pour la forme " + s.name());
		Player lowestValue = null;
		Card card = null;
		
		for (int i = 0; i < this.players.size(); i++) { //Run each player
			
			for (int k = 0; k < this.players.get(i).getJest().getCards().size(); k++) { //Run each player card jest
				
				if(lowestValue == null && this.players.get(i).getJest().getCards().get(k).getShape().equals(s)) {
					//If there is no player in the variable highestValue and the current player jest card has the same shape of the condition, then change the variables below
					lowestValue = this.players.get(i);
					card = this.players.get(i).getJest().getCards().get(k); //Store its jest card
				}
				
				if(this.players.get(i).getJest().getCards().get(k).getShape().equals(s)) {
					if(this.players.get(i).getJest().getCards().get(k).getValue() < card.getValue()) {
						lowestValue = this.players.get(i);
						card = this.players.get(i).getJest().getCards().get(k); //Store its jest card
					}
				} //End if equals to s
			} //End run each player card
		} //End run each player
		
		System.out.println("Le gagnant de la condition LOWEST_" + s.name().toUpperCase() + " est " + lowestValue.getNickname());
		return lowestValue;
//		for (int i = 0; i < this.trophies.getCards().size(); i++) {
//			if(s.equals(Shape.CLUBS)) {
//				if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.HIGHTEST_CLUBS)) {
//					this.trophies.addACardFromAPacketToAnotherPacket(i, lowestValue.getJest());
//				}
//			} else if(s.equals(Shape.SPADES)) {
//				if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.HIGHTEST_SPADES)) {
//					this.trophies.addACardFromAPacketToAnotherPacket(i, lowestValue.getJest());
//				}
//			} else if(s.equals(Shape.HEARTS)) {
//				if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.HIGHTEST_HEARTS)) {
//					this.trophies.addACardFromAPacketToAnotherPacket(i, lowestValue.getJest());
//				}
//			} else if(s.equals(Shape.DIAMONDS)) {
//				if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.HIGHTEST_DIAMONDS)) {
//					this.trophies.addACardFromAPacketToAnotherPacket(i, lowestValue.getJest());
//				}
//			}
//			
//		}
	}
	
	public Player calculateMajority(int value) {
		Player winner = null;
		Map<Player, Integer> tab = new HashMap<>();
		
		for (int i = 0; i < this.players.size(); i++) {
			tab.put(this.players.get(i), 0);
			for (int j = 0; j < this.players.get(i).getJest().getCards().size(); j++) {
				if(this.players.get(i).getJest().getCards().get(j).getValue() == value) {
					tab.put(this.players.get(i), tab.get(this.players.get(i))+1);
				}
			}
			
		}
		
		//System.out.println(tab);
		List<Player> tabPlayers = new ArrayList<Player>();
		
		for (int i = 0; i < this.players.size(); i++) {
			
			if(tabPlayers.size() == 0) {
				//If the size equals 0 add to the tabPlayers the first player of the loop
				tabPlayers.add(this.players.get(i));
				
			} else if(tab.get(tabPlayers.get(0)) < tab.get(this.players.get(i))) {
				//If a player in the tabPlayers has majority value < the current player, clear the tabPlayers and add the current player
				tabPlayers.clear();
				tabPlayers.add(this.players.get(i));
				
			} else if(tab.get(tabPlayers.get(0)) == tab.get(this.players.get(i))) {
				// If a player has the same majority value, add it to tabPlayers
					tabPlayers.add(this.players.get(i));
			}
		}
		
		//System.out.println("\nCelui ou ceux qui ont la majorit� de " + value + ", est : " + tabPlayers);
		
		if(tabPlayers.size() > 1) {
			
			Map<Player, Card> playersSortedByShape = new HashMap<>();
		for (int i = 0; i < tabPlayers.size(); i++) {
			
			//Get the best card (best shape) of the suit from the value number
			Map<Player, Card> currentPlayerSortedByShape = new HashMap<>();
			
			for (int j = 0; j < tabPlayers.get(i).getJest().getCards().size(); j++) {
				//System.out.println(currentPlayerSortedByShape);
				if(currentPlayerSortedByShape.size() == 0 && tabPlayers.get(i).getJest().getCards().get(j).getValue() == value) {
					//We add the first card
					currentPlayerSortedByShape.put(tabPlayers.get(i), tabPlayers.get(i).getJest().getCards().get(j));
				} else if(currentPlayerSortedByShape.size() > 0 && tabPlayers.get(i).getJest().getCards().get(j).getShape().ordinal() > currentPlayerSortedByShape.get(tabPlayers.get(i)).getShape().ordinal() && tabPlayers.get(i).getJest().getCards().get(j).getValue() == value) {
					
					currentPlayerSortedByShape.put(tabPlayers.get(i), tabPlayers.get(i).getJest().getCards().get(j));
				}
			}
			
			//Compare its best card shape value to the one of the player in the playersSortedByShape
			if(playersSortedByShape.size() == 0) {
				playersSortedByShape.put(tabPlayers.get(i), currentPlayerSortedByShape.get(tabPlayers.get(i)));
			} else if(currentPlayerSortedByShape.get(tabPlayers.get(i)).getShape().ordinal() > playersSortedByShape.entrySet().iterator().next().getValue().getShape().ordinal()) {
				playersSortedByShape.clear();
				playersSortedByShape.put(tabPlayers.get(i), currentPlayerSortedByShape.get(tabPlayers.get(i)));
			}
			
			currentPlayerSortedByShape.clear();
		}
		//System.out.println("\ngagnant !!! : " + playersSortedByShape);
		
		Map.Entry<Player,Card> entry = playersSortedByShape.entrySet().iterator().next();
		 
		winner = entry.getKey();
	} else {
		winner = tabPlayers.get(0);
	}
		
		System.out.println("Le gagnant de la condition MAJORITY_" + value  + " est " + winner.getNickname());
		return winner;
//		for (int i = 0; i < this.trophies.getCards().size(); i++) {
//			if(value == 2) {
//				if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.MAJORITY_2)) {
//					this.trophies.addACardFromAPacketToAnotherPacket(i, winner.getJest());
//				}
//			} else if(value == 3) {
//				if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.MAJORITY_3)) {
//					this.trophies.addACardFromAPacketToAnotherPacket(i, winner.getJest());
//				}
//			} else if(value == 4) {
//				if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.MAJORITY_4)) {
//					this.trophies.addACardFromAPacketToAnotherPacket(i, winner.getJest());
//				}
//			}
//			
//		}
		
	}
	
	public Player calculateBestJest(boolean joker) {
		Packet jokerPacket = new Packet(new ArrayList<Card>());
		Player playerWithJoker = null;
		
		for (int i = 0; i < this.players.size(); i++) {
			if(joker == false) {
				for (int j = 0; j < this.players.get(i).getJest().getCards().size(); j++) {
					if(this.players.get(i).getJest().getCards().get(j).getShape().equals(Shape.JOKER)) {
						this.players.get(i).getJest().addACardFromAPacketToAnotherPacket(j, jokerPacket);
						playerWithJoker = this.players.get(i);
					}
				}
				
			}
			System.out.println(this.players.get(i).getNickname());
			System.out.println(this.players.get(i).getJest());
			System.out.println("--*-*-*-*-*-*-*-*-*-*-*-*-*-*");
			
			this.players.get(i).getJest().accepterVisiteur(this);
			
			System.out.println("le score de " + this.players.get(i).getNickname() + " est de " + this.players.get(i).getJest().getScore());
			System.out.println("--*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		}
		
		if(joker == false) {
			jokerPacket.addACardFromAPacketToAnotherPacket(0, playerWithJoker.getJest());
		}
		
		
		Player winner = null;
		for (int i = 0; i < this.players.size(); i++) {
			if(winner == null) {
				winner = this.players.get(i);
			} else if(this.players.get(i).getJest().getScore() > winner.getJest().getScore()) {
				winner = this.players.get(i);
			}
		}
		
		System.out.println("Le gagnant de la condition BEST_JEST est " + winner.getNickname());
		return winner;
//		for (int i = 0; i < this.trophies.getCards().size(); i++) {
//			if(this.trophies.getCards().get(i).getJestValue().equals(JestValue.BEST_JEST)) {
//				this.trophies.addACardFromAPacketToAnotherPacket(i, winner.getJest());
//			}
//		}
	}
	
	public Player calculateJoker() {
		Player winner = null;
		for (int i = 0; i < this.players.size(); i++) {
			for (int j = 0; j < this.players.get(i).getJest().getCards().size(); j++) {
				if(this.players.get(i).getJest().getCards().get(j).getShape().equals(Shape.JOKER)) {
					System.out.println("Le gagnant de la condition JOKER est " + this.players.get(i).getNickname());
					winner = this.players.get(i);
//					for (int k = 0; k < this.trophies.getCards().size(); k++) {
//						if(this.trophies.getCards().get(k).getJestValue().equals(JestValue.BEST_JEST)) {
//							this.trophies.addACardFromAPacketToAnotherPacket(k, this.players.get(i).getJest());
//						}
//					}
				}
			}
		}
		
		return winner;
		
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
				playersSortedByValue.add(this.players.get(i));
			} else {
				for (int j = 0; j < playersSortedByValue.size(); j++) {
					if(this.players.get(i).getHand().getCards().get(0).getValue() == playersSortedByValue.get(j).getHand().getCards().get(0).getValue()) {
				
						//If the player in the sortedValue tab has the same value as the player in players tab, add it to the sortedValue tab
						playersSortedByValue.add(this.players.get(i));
						break;
					} else if(this.players.get(i).getHand().getCards().get(0).getValue() > playersSortedByValue.get(j).getHand().getCards().get(0).getValue()) {
						playersSortedByValue.clear();
						playersSortedByValue.add(this.players.get(i));
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
		
		//this.notifyStartPlateau(this.reference_card.getPath(), this.players.size(), this.deck.getCards().size());
	}
	
	public void initializeDeck() {

		//SPADES, CLUBS, DIAMONDS, HEARTS
		//HIGHTEST, LOWWEST, MAJORITY, BEST_JEST, BEST_JEST_NOJOKER, JOKER, REFERENCE_CARD;
		
		
		Card joker = new Card(0, false, false, Shape.JOKER, JestValue.BEST_JEST, "/17.png");
		Card referenceCard = new Card(-1, false, false, Shape.REFERENCE_CARD, JestValue.NONE, "/18.png");
		
		Card Spades5 = new Card(5, false, false, Shape.SPADES, JestValue.HIGHTEST_CLUBS, "/4.png"); //Ace
		Card Clubs5 = new Card(5, false, false, Shape.CLUBS, JestValue.HIGHTEST_SPADES, "/3.png"); //Ace
		Card Diamonds5 = new Card(5, false, false, Shape.DIAMONDS, JestValue.MAJORITY_4, "/2.png"); //Ace
		Card Hearts5 = new Card(5, false, false, Shape.HEARTS, JestValue.JOKER, "/1.png"); //Ace
		
		Card Spades4 = new Card(4, false, false, Shape.SPADES, JestValue.LOWEST_CLUBS, "/16.png");
		Card Clubs4 = new Card(4, false, false, Shape.CLUBS, JestValue.LOWEST_SPADES, "/15.png");
		Card Diamonds4 = new Card(4, false, false, Shape.DIAMONDS, JestValue.BEST_JEST_NOJOKER, "/14.png");
		Card Hearts4 = new Card(4, false, false, Shape.HEARTS, JestValue.JOKER, "/13.png");
		
		Card Spades3 = new Card(3, false, false, Shape.SPADES, JestValue.MAJORITY_2, "/12.png");
		Card Clubs3 = new Card(3, false, false, Shape.CLUBS, JestValue.HIGHTEST_HEARTS, "/11.png");
		Card Diamonds3 = new Card(3, false, false, Shape.DIAMONDS, JestValue.LOWEST_DIAMONDS, "/10.png");
		Card Hearts3 = new Card(3, false, false, Shape.HEARTS, JestValue.JOKER, "/9.png");
		
		Card Spades2 = new Card(2, false, false, Shape.SPADES, JestValue.MAJORITY_3, "/8.png");
		Card Clubs2 = new Card(2, false, false, Shape.CLUBS, JestValue.LOWEST_HEARTS, "/7.png");
		Card Diamonds2 = new Card(2, false, false, Shape.DIAMONDS, JestValue.HIGHTEST_DIAMONDS, "/6.png");
		Card Hearts2 = new Card(2, false, false, Shape.HEARTS, JestValue.JOKER, "/5.png");
		
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
		this.reference_card = referenceCard;
		//this.notifyStartPlateau(referenceCard.getPath(), this.players.size(), this.deck.getCards().size());
		this.initializeTrophies();
		this.notifyStartPlateau(this.reference_card.getPath(), this.players.size(), this.deck.getCards().size());
	}
	
	public void initializeTrophies() {
		
		this.deck.shuffleCards(); //We shuffle the deck
		this.trophies = new Packet(new ArrayList<Card>()); //We set the variable as a new Packet
		
		if(this.players.size() == 4) { //If there are 4 players
			
			//Add 1 Card to the trophies
			this.deck.addACardFromAPacketToAnotherPacket(0, this.trophies);
			
		} else { //If there are 3 players
			
			//Add 2 random Cards to the trophies
//			for (int i = 0; i < this.deck.getCards().size(); i++) {
//				if(this.trophies.getCards().size() < 2) {
//					
//					if(this.deck.getCards().get(i).getJestValue().equals(JestValue.BEST_JEST_NOJOKER)) { //Testing majority calculation
//						this.deck.addACardFromAPacketToAnotherPacket(i, this.trophies);
//					} else if(this.deck.getCards().get(i).getJestValue().equals(JestValue.MAJORITY_3)) { //Testing majority calculation
//						this.deck.addACardFromAPacketToAnotherPacket(i, this.trophies);
//					} else if(this.deck.getCards().get(i).getJestValue().equals(JestValue.MAJORITY_2)) { //Testing majority calculation
//						this.deck.addACardFromAPacketToAnotherPacket(i, this.trophies);
//					}
//				}
//				
//			}
			this.deck.addACardFromAPacketToAnotherPacket(0, this.trophies);
			this.deck.addACardFromAPacketToAnotherPacket(1, this.trophies);
		}
		
		
		this.playRounds();
	}
	
	
	public void initializePlayers() {
		Scanner sc = new Scanner(System.in);
		int nbPlayers = 0;
		int nbRealPlayers = 0;
		
		//Ask the amount of players wanted
		System.out.println("Saisissez le nombre de joueurs souhait� pour cette partie (3 ou 4)");
		
		nbPlayers = sc.nextInt();
		while(nbPlayers != 3 && nbPlayers != 4) {
			System.out.println("Vous devez choisir 3 ou 4 :");
			nbPlayers = sc.nextInt();
		}
		
		//Ask the amount of REAL players wanted
		System.out.println("Saisissez le nombre de joueurs R�ELS parmis ces " + nbPlayers + " joueurs :");
		
		nbRealPlayers = sc.nextInt();
		while(nbRealPlayers < 0 || nbRealPlayers > nbPlayers) {
			System.out.println("Vous devez choisir un nombre compris entre 1 et " + nbPlayers + " :");
			nbRealPlayers = sc.nextInt();
		}
		
		if(nbPlayers != nbRealPlayers) {
			System.out.println("Il y a donc " + nbRealPlayers + " joueurs r�els, et " + (nbPlayers - nbRealPlayers) + " joueurs Virtuels.");
		}
		
		//Ask the level of difficulty for each virtual players
		int requests = nbPlayers - nbRealPlayers;
		
		for (int i = 1; i <= requests; i++) {
				System.out.println("Choisissez un niveau de difficult� pour le joueur virtuel num�ro " + i + "(easy : 1, hard : 2)");
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
				System.out.println("D�finissez un nom pour le joueur r�el num�ro " + i + " : ");
				String answer = sc.next();
				
				while(answer.length() > 20) {
					System.out.println("Choisissez un nom moins long !");
					answer = sc.next();
				}
				
				this.players.add(new RealPlayer(answer));
			}
			
		}
		
		//System.out.println(this.players);
//		if(this.players.size() == 3 || this.players.size() == 4) {
//			this.initializeDeck();
//		}
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
