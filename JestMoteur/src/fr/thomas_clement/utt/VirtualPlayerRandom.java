package fr.thomas_clement.utt;

import java.util.List;
import java.util.Random;

public class VirtualPlayerRandom implements Strategy{

	@Override
	public int makeOffers(Player player) {
		int offerHidden = new Random().nextInt((2 - 1) + 1) + 1;
		//player.getHand().getCards().get(offerHidden).setFaceHidden(true);
		
		return offerHidden;
		
	}

	@Override
	public Object[] chooseOffers(List<Player> tabPlayers, Player p) {
		
		Player playerChosen = tabPlayers.get((new Random().nextInt(((tabPlayers.size()-1) - 0) + 1) + 0));
		int offerTaken = new Random().nextInt((2 - 1) + 1) + 1;
		
		
		return new Object[] {playerChosen, offerTaken};
		
	}

	


}
