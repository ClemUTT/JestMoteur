package fr.thomas_clement.utt;

/**
 * Type de joueur virtuel IA qui h�rite de la classe joueur
 */
public class VirtualPlayer extends Player{
	
	/**
	 * Permet de donner un nom unique � chaque joueur virtuel cr��
	 */
	private static int NBRVIRTUALPLAYERS = 0; //To decide the nickname of a virtual player 
	
	/**
	 * Constructeur de la classe
	 * Permet de donner un nom unique � chaque joueur virtuel cr�� et d�finie la strat�gie voulue qui a �t� mise en param�tre en appelant la classe m�re du constructeur
	 * @param strategy
	 * 				Choix de la strategie du joueur virtuel
	 * 
	 */
	public VirtualPlayer(Strategy strategy) {
		super("vp" + VirtualPlayer.NBRVIRTUALPLAYERS, strategy);
		VirtualPlayer.NBRVIRTUALPLAYERS += 1;
	}

}
