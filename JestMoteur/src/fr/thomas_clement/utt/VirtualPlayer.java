package fr.thomas_clement.utt;

/**
 * Type de joueur virtuel IA qui hérite de la classe joueur
 */
public class VirtualPlayer extends Player{
	
	/**
	 * Permet de donner un nom unique à chaque joueur virtuel créé
	 */
	private static int NBRVIRTUALPLAYERS = 0; //To decide the nickname of a virtual player 
	
	/**
	 * Constructeur de la classe
	 * Permet de donner un nom unique à chaque joueur virtuel créé et définie la stratégie voulue qui a été mise en paramètre en appelant la classe mère du constructeur
	 * @param strategy
	 * 				Choix de la strategie du joueur virtuel
	 * 
	 */
	public VirtualPlayer(Strategy strategy) {
		super("vp" + VirtualPlayer.NBRVIRTUALPLAYERS, strategy);
		VirtualPlayer.NBRVIRTUALPLAYERS += 1;
	}

}
