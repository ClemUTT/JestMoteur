package fr.thomas_clement.utt;

import java.util.List;
import java.util.Scanner;

public class RealPlayerStrategy implements Strategy{

	@Override
	public void makeOffers(Player p) {
		System.out.println(p.getNickname() + ", voici vos offres : ");
		for (int i = 0; i < p.getHand().getCards().size(); i++) {
			System.out.println("offre " + (i+1) + " : " + p.getHand().getCards().get(i));
		}
		
		System.out.println("Choisissez l'offre que vous ne souhaitez PAS révéler (1 ou 2)");
		Scanner sc = new Scanner(System.in);
		int answer = sc.nextInt();
		
		while(answer != 1 && answer != 2) {
			System.out.println("Veuillez choisir un nombre entre 1 et 2 : ");
			answer = sc.nextInt();
		}
		p.getHand().getCards().get(answer - 1).setFaceHidden(true);
	}

	
	@Override
	public void chooseOffers(List<Player> players) {
		
		
	}

}
