package fr.thomas_clement.utt;

public class RealPlayer extends Player{

	public RealPlayer(String nickname) {
		super(nickname, new RealPlayerStrategy());
	}

}
