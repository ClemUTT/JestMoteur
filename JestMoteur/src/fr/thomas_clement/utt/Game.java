package fr.thomas_clement.utt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	List<Player> players = new ArrayList<Player>();
	
	public Game() {
		
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
		
		System.out.println(this.players);
	
	}

	
	
	
	
}
