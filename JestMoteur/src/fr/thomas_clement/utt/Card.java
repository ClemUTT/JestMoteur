package fr.thomas_clement.utt;

public class Card {

	private int value;
	private boolean faceHidden = false;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isFaceHidden() {
		return faceHidden;
	}

	public void setFaceHidden(boolean faceHidden) {
		this.faceHidden = faceHidden;
	}

	public boolean isTrophee() {
		return isTrophee;
	}

	public void setTrophee(boolean isTrophee) {
		this.isTrophee = isTrophee;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public JestValue getJestValue() {
		return jestValue;
	}

	public void setJestValue(JestValue jestValue) {
		this.jestValue = jestValue;
	}
	
	

}
