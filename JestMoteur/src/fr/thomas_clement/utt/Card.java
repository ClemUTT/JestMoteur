package fr.thomas_clement.utt;

/**
 * Type Carte 
 */
public class Card {

	/**
	 * Valeur de la carte (entier)
	 */
	private int value;
	
	/**
	 * Carte face cachée : vrai ou faux
	 */
	private boolean faceHidden = false;
	
	/**
	 * Carte trophée : vrai ou faux
	 */
	private boolean isTrophee;
	
	/**
	 * Couleur de la carte : trèfle, carreau, pique, coeur
	 * @see Shape
	 */
	private Shape shape;
	
	/**
	 * Valeur de jest de la carte
	 * @see JestValue
	 */
	private JestValue jestValue;
	
	/**
	 * Chemin vers l'image de la carte pour l'interface graphique
	 */
	private String path;
	
	/**
	 * Constructeur de la classe
	 * @param value
	 * 				Valeur de la carte (entier)
	 * @param faceHidden
	 * 				Carte face cachée : vrai ou faux
	 * @param isTrophee
	 * 				Carte trophée : vrai ou faux
	 * @param shape
	 * 				Couleur de la carte : trèfle, carreau, pique, coeur
	 * @param jestValue
	 * 				Valeur de jest de la carte
	 * @param path
	 * 				Chemin vers l'image de la carte pour l'interface graphique
	 */
	public Card(int value, boolean faceHidden, boolean isTrophee, Shape shape, JestValue jestValue, String path) {
		this.value = value;
		this.faceHidden = faceHidden;
		this.isTrophee = isTrophee;
		this.shape = shape;
		this.jestValue = jestValue;
		this.path = path;
	}
	/**
	 * Décrit l'objet de la classe sous forme d'une chaine de caracteres
	 * @return Chaine de caractere de la carte 
	 */
	public String toString() {
		
		StringBuffer bf = new StringBuffer();
		
		if(this.faceHidden) {
			bf.append("face hidden \n");
		} else {
			bf.append(this.value);
			bf.append(" ; " + this.shape);
			bf.append(" ; " + this.jestValue);
			bf.append("\n");
		}
		
		
		
		return bf.toString();
	}

	/**
	 * Retourner la valeur
	 * @return valeur de la carte
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Modifier la valeur entière
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Retourner la valeur
	 * @return face cachée vrai ou faux 
	 */
	public boolean isFaceHidden() {
		return faceHidden;
	}
	
	/**
	 * Modifier la valeur face cahée oui on non
	 */
	public void setFaceHidden(boolean faceHidden) {
		this.faceHidden = faceHidden;
	}
	
	/**
	 * Retourner la valeur
	 * @return Trophée ou non
	 */
	public boolean isTrophee() {
		return isTrophee;
	}

	/**
	 * Modifier la valeur : est un trophée oui ou non
	 */
	public void setTrophee(boolean isTrophee) {
		this.isTrophee = isTrophee;
	}
	
	/**
	 * Retourner la valeur
	 * @return Couleur de type Couleur
	 */
	public Shape getShape() {
		return shape;
	}
	
	/**
	 * Modifier la valeur : couleur de la carte
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}

	/**
	 * Retourner la valeur
	 * @return Valeur de Jest du type valeur de jest
	 */
	public JestValue getJestValue() {
		return jestValue;
	}
	
	/**
	 * Modifier la valeur de jest de la carte
	 */
	public void setJestValue(JestValue jestValue) {
		this.jestValue = jestValue;
	}

	/**
	 * Retourner la valeur
	 * @return chemin vers l'image de la carte
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Modifier la valeur du chemin de l'image de la carte
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	

}
