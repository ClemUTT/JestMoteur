package fr.thomas_clement.utt;

/**
 * Il s'agit de l'interface Visitable du patron de conception Visitor
 * Elle sera impl�ment�e par les classes qui seront visitable par les classe Visiteur
 */
public interface Visitable {

	/**
	 * M�thode qui permet d'accpeter les objet de type Visiteur
	 * @param v
	 * 				l'objet qui visitera la classe de type Visitable
	 */
	public void accepterVisiteur(Visiteur v);
	
}
