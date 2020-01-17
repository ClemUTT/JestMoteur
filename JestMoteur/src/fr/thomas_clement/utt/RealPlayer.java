package fr.thomas_clement.utt;

/**
 * Type de joueur r�el qui h�rite de la classe Joueur
 */
public class RealPlayer extends Player{
	
	/**
	 * Constructeur de la classe
	 * Appelle le constructeur de la classe m�re en prenant en 2�me param�tre la strat�gie d'un joueur r��l
	 * @param nickname
	 * 				Pseudo du joueur
	 */
	public RealPlayer(String nickname) {
		super(nickname, new RealPlayerStrategy());
	}

}
