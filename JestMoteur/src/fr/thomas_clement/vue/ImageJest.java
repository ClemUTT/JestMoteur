package fr.thomas_clement.vue;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * Classe qui est hérite de ImageIcon et qui permet de configuer une image (en l'occurence ici une carte)
 * avec une taille par défaut
 */
public class ImageJest extends ImageIcon{
	
	/**
	 * Constructeur de la classe ImageJest prenant l'URL de l'image et lui définissant une taille qui peut être modifiée par la suite
	 * 
	 * @param url
	 * 				prend l'URL de l'image
	 */
	public ImageJest(URL url) {
		super(url);
		super.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
	}
	
	
}
