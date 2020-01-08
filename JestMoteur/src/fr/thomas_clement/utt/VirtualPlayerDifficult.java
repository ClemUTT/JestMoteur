package fr.thomas_clement.utt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VirtualPlayerDifficult implements Strategy{

	@Override
	public int makeOffers(Player player) {
		
		int offerHidden = new Random().nextInt((2 - 1) + 1) + 1;
		//player.getHand().getCards().get(offerHidden).setFaceHidden(true);
		
		return offerHidden;
		
	}

	@Override
	public Object[] chooseOffers(List<Player> players, Player p) {
		
		List<Player> bestPlayer = new ArrayList<>();
		bestPlayer.add(players.get(0));
		int bestOffer = 1;
		List<Card> bestCard = new ArrayList<>();
		
		for (int i = 0; i < players.size(); i++) {
			
			for (int j = 0; j < players.get(i).getHand().getCards().size(); j++) {
				if(players.get(i).getHand().getCards().get(j).isFaceHidden() == false) {
					if(players.get(i).getHand().getCards().get(j).getShape().equals(Shape.SPADES) || players.get(i).getHand().getCards().get(j).getShape().equals(Shape.CLUBS)) {
						if(bestCard.size() == 0) {
							bestPlayer.clear();
							bestCard.clear();
							bestPlayer.add(players.get(i));
							bestCard.add(players.get(i).getHand().getCards().get(j));
							bestOffer = j+1;
						}
						
						if(players.get(i).getHand().getCards().get(j).getShape().ordinal() > bestCard.get(0).getShape().ordinal()) {
							bestPlayer.clear();
							bestCard.clear();
							bestPlayer.add(players.get(i));
							bestCard.add(players.get(i).getHand().getCards().get(j));
							bestOffer = j+1;
						}
					} else {
						
						if(bestCard.size() == 0) {
							bestPlayer.clear();
							bestCard.clear();
							bestPlayer.add(players.get(i));
							bestCard.add(players.get(i).getHand().getCards().get(j));
							bestOffer = j+1;
						}
						
						if(players.get(i).getHand().getCards().get(j).getShape().ordinal() > bestCard.get(0).getShape().ordinal()) {
							bestPlayer.clear();
							bestCard.clear();
							bestPlayer.add(players.get(i));
							bestCard.add(players.get(i).getHand().getCards().get(j));
							bestOffer = j+1;
						}
					}
				}
			}
		}
		
		return new Object[] {bestPlayer.get(0), bestOffer};
	}

	

}
