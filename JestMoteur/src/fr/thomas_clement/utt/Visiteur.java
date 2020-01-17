package fr.thomas_clement.utt;


/**
 * Il s'agit de l'interface Visiteur du patron de conception Visitor
 * Elle sera impl�ment�e par les classes qui visiteront la classe visitable
 */
public interface Visiteur {

	/**
	 * M�thode qui permet de la calculter le score de la classe de type Visitable
	 * @param visitable
	 * 				l'objet qui sera visit�
	 */
	public void calculerScore(Jester visitable);
	
}
