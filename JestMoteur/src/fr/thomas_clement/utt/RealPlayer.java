package fr.thomas_clement.utt;

/**
 * Type de joueur réel qui hérite de la classe Joueur
 */
public class RealPlayer extends Player{
	
	/**
	 * Constructeur de la classe
	 * Appelle le constructeur de la classe mère en prenant en 2ème paramètre la stratégie d'un joueur réél
	 * @param nickname
	 * 				Pseudo du joueur
	 */
	public RealPlayer(String nickname) {
		super(nickname, new RealPlayerStrategy());
	}

}
