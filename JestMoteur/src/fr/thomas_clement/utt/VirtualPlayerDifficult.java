package fr.thomas_clement.utt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VirtualPlayerDifficult implements Strategy{

	@Override
	public int makeOffers(Player player) {
		
		int offerHidden = (new Random().nextInt((2 - 1) + 1) + 1);
		return offerHidden;
		
	}

	@Override
	public Object[] chooseOffers(List<Player> players, Player p) {
		
		List<Player> bestPlayer = new ArrayList<>();
		bestPlayer.add(players.get(0));
		int bestOffer = new Random().nextInt((2 - 1) + 1) + 1;
		
		for (int k = 0; k < players.size(); k++) {
			if(players.get(k).getHand().getCards().get(0).getShape().equals(Shape.SPADES) || players.get(k).getHand().getCards().get(0).getShape().equals(Shape.CLUBS)) {
				bestOffer = 1;
				bestPlayer.add(players.get(k));
			} else {
				bestOffer = 2;
				bestPlayer.add(players.get(k));
			}
		}
		
		
						
		
		return new Object[] {bestPlayer.get(0), bestOffer};
	}

	

}
