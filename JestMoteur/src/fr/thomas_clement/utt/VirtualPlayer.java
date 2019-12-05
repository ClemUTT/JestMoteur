package fr.thomas_clement.utt;

public class VirtualPlayer extends Player{
	
	private static int NBRVIRTUALPLAYERS = 0; //To decide the nickname of a virtual player 

	public VirtualPlayer(Strategy strategy) {
		super("vp" + VirtualPlayer.NBRVIRTUALPLAYERS, strategy);
		VirtualPlayer.NBRVIRTUALPLAYERS += 1;
	}

}
