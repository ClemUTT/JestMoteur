package fr.thomas_clement.utt;

public class Card {

	private int value;
	private boolean faceHidden;
	private boolean isTrophee;
	private Shape shape;
	private JestValue jestValue;
	
	public Card(int value, boolean faceHidden, boolean isTrophee, Shape shape, JestValue jestValue) {
		this.value = value;
		this.faceHidden = faceHidden;
		this.isTrophee = isTrophee;
		this.shape = shape;
		this.jestValue = jestValue;	
	}
	
	public String toString() {
		
		StringBuffer bf = new StringBuffer();
		
		bf.append(this.value);
		bf.append(" ; " + this.shape);
		bf.append(" ; " + this.jestValue);
		bf.append(" ; " + this.faceHidden);
		bf.append("\n");
		
		return bf.toString();
	}

}
