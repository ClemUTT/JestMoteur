package fr.thomas_clement.utt;


/**
 * Il s'agit de l'interface Visiteur du patron de conception Visitor
 * Elle sera implémentée par les classes qui visiteront la classe visitable
 */
public interface Visiteur {

	/**
	 * Méthode qui permet de la calculter le score de la classe de type Visitable
	 * @param visitable
	 * 				l'objet qui sera visité
	 */
	public void calculerScore(Jester visitable);
	
}
