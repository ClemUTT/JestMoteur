package fr.thomas_clement.vue;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageJest extends ImageIcon{
	
	private int width;
	private int height;

	public ImageJest(URL url) {
		super(url);
		super.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
	}
	
	
}
