package fr.thomas_clement.utt;

import java.util.List;

public interface Strategy {
	
	abstract public void makeOffers(Player player);
	
	abstract public void chooseOffers(List<Player> players);
	

}
