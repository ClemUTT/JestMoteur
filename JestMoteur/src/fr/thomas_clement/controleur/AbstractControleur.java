package fr.thomas_clement.controleur;

import fr.thomas_clement.modele.AbstractGame;

public abstract class AbstractControleur {

	protected AbstractGame game;
	
	public AbstractControleur(AbstractGame game) {
		this.game = game;
	}
	
	public void setNombreJ(int nb, int[] niveaux) {
		this.game.setNbPlayers(nb);
		//griseRadioButton("j");
		this.startParty(niveaux[0], niveaux[1]);
	}
	
	public void setNombreRJ(int nb, int[] niveaux) {
		this.game.setNbRealPlayers(nb);
		//griseRadioButton("r");
		this.startParty(niveaux[0], niveaux[1]);
	}
	
	public void setNombreVJ(int nb, int[] niveaux) {
		this.game.setNbVirtualPlayers(nb);
		//griseRadioButton("v");
		this.startParty(niveaux[0], niveaux[1]);
		
	}
	
	public boolean startParty(int niv1, int niv2) {
		
		if(((niv1 + niv2) == this.game.getNbVirtualPlayers() && this.game.getNbVirtualPlayers() > 0)) {
			System.out.println("C'est bon");
			System.out.println("niv1 et niv2 : " + niv1 + " " + niv2);
			System.out.println(this.game.getNbVirtualPlayers());
			this.game.setNiv(niv1, niv2);
			this.game.setReadyToPlay(true);
			griseRadioButton("j");
			return true;
		} else {
			System.out.println("Pas bon !!!!");
			System.out.println(this.game.getNbVirtualPlayers());
			System.out.println("niv1 et niv2 : " + niv1 + " " + niv2);
			this.game.setReadyToPlay(false);
			griseRadioButton("j");
			return false;
		}
		
		
	}
	
	
	public void jouer() {
		
	}
	
	abstract public void griseRadioButton(String natureJoueur);
	
}
