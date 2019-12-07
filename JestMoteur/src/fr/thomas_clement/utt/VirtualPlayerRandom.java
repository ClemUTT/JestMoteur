package fr.thomas_clement.utt;

import java.util.List;

public class VirtualPlayerRandom implements Strategy{

	@Override
	public void makeOffers(Player player) {
		int offerHidden = (int) (Math.random() * (3 - 1));
		player.getHand().getCards().get(offerHidden).setFaceHidden(true);
		
	}

	@Override
	public void chooseOffers(List<Player> players) {
		// TODO Auto-generated method stub
		
	}

	


}
