package fr.thomas_clement.utt;

import java.util.List;

public interface Strategy {
	
	abstract public int makeOffers(Player player);
	
	abstract public Object[] chooseOffers(List<Player> players, Player player);
	

}
