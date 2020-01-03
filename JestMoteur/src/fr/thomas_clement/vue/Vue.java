package fr.thomas_clement.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import fr.thomas_clement.controleur.AbstractControleur;
import fr.thomas_clement.observer.Observer;

public class Vue extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	private AbstractControleur controleur;
	private JRadioButton jr[] = {new JRadioButton("1"), new JRadioButton("2"), new JRadioButton("3"), new JRadioButton("4")};
	private JRadioButton jv[] = {new JRadioButton("1"), new JRadioButton("2"), new JRadioButton("3"), new JRadioButton("4")};

	public Vue(AbstractControleur controleur) {
		super("Jest (Thomas et Clément)");
		this.controleur = controleur;
		init();
	}
	
	@Override
	public void updateStart(int nbReels, int nbVirtuels) {
		for (int i = 0; i < nbReels; i++) {
			//real players number selectionable
			this.jr[i].setEnabled(true);
		}
		
		for (int i = nbReels; i < jr.length; i++) {
			//real players number UNselectionable
			this.jr[i].setEnabled(false);
		}
		
		
		for (int i = 0; i < nbVirtuels; i++) {
			//virtual players number selectionable
			this.jv[i].setEnabled(true);
		}
		
		for (int i = nbVirtuels; i < jr.length; i++) {
			//virtual players number UNselectionable
			this.jv[i].setEnabled(false);
		}
		
		
	}
	
	public void init() {
		this.setVisible(true);
		//Set size of the JFrame
		//this.setSize(500, 500);
		//this.pack();
		//Full screen
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//Set location relative to the screen
		this.setLocationRelativeTo(null);
		//When you close the window it also terminates / Kills the program
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel content = (JPanel) this.getContentPane();
		//Add a layout to organizer each element
		content.add(this.createInitializationWindow(), BorderLayout.CENTER);
		content.add(new JButton("JOUER"), BorderLayout.SOUTH);
		this.pack();
		
		
	}
	
	public JPanel createInitializationWindow() {
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(1, 4));
		
		JPanel content2 = new JPanel();
		content2.setLayout(new FlowLayout());
		
		//Boutons nombre de joueurs
		JLabel nbJoueurs = new JLabel("Nombre de joueurs");
		JLabel nbJoueursReels = new JLabel("Nombre de joueurs réels");
		JLabel nbJoueursVirtuels = new JLabel("Nombre de joueurs virtuels");
		JLabel niveauDifficulte = new JLabel("Niveau de difficulté");
		
		ButtonGroup groupeJ = new ButtonGroup();
		ButtonGroup groupeJR = new ButtonGroup();
		ButtonGroup groupeJV = new ButtonGroup();
		ButtonGroup groupeNiv = new ButtonGroup();
		
		JRadioButton j1 = new JRadioButton("3");
		//j1.setEnabled(false);
		JRadioButton j2 = new JRadioButton("4");
		
		JRadioButton jniv1 = new JRadioButton("1");
		JRadioButton jniv2 = new JRadioButton("2");
		
		groupeJ.add(j1);
		groupeJ.add(j2);
		
		groupeJR.add(jr[0]);
		groupeJR.add(jr[1]);
		groupeJR.add(jr[2]);
		groupeJR.add(jr[3]);
		
		groupeJV.add(jv[0]);
		groupeJV.add(jv[1]);
		groupeJV.add(jv[2]);
		groupeJV.add(jv[3]);
		
		groupeNiv.add(jniv1);
		groupeNiv.add(jniv2);
		

		
	


		
//		JPanel first = new JPanel();
//		first.add(nbJoueurs, BorderLayout.WEST);
//		
//		JPanel second = new JPanel();
//		second.add(nbJoueursReels, BorderLayout.CENTER);
//		
//		JPanel third = new JPanel();
//		third.add(nbJoueursVirtuels, BorderLayout.EAST);
//		
		JPanel first = new JPanel();
		first.setLayout(new GridLayout(2, 1));
		JPanel firstRadio = new JPanel();
		firstRadio.setLayout(new FlowLayout());
		firstRadio.add(j1);
		firstRadio.add(j2);
		
		
		first.add(nbJoueurs);
		first.add(firstRadio);
		
		JPanel second = new JPanel();
		second.setLayout(new GridLayout(2, 1));
		JPanel secondRadio = new JPanel();
		secondRadio.setLayout(new FlowLayout());
		secondRadio.add(jr[0]);
		secondRadio.add(jr[1]);
		secondRadio.add(jr[2]);
		secondRadio.add(jr[3]);
		
		second.add(nbJoueursReels);
		second.add(secondRadio);
		
		JPanel third = new JPanel();
		third.setLayout(new GridLayout(2, 1));
		JPanel thirdRadio = new JPanel();
		thirdRadio.setLayout(new FlowLayout());
		thirdRadio.add(jv[0]);
		thirdRadio.add(jv[1]);
		thirdRadio.add(jv[2]);
		thirdRadio.add(jv[3]);
		
		third.add(nbJoueursVirtuels);
		third.add(thirdRadio);
		
		JPanel fourth = new JPanel();
		fourth.setLayout(new GridLayout(2, 1));
		JPanel fourthRadio = new JPanel();
		fourthRadio.setLayout(new FlowLayout());
		fourthRadio.add(jniv1);
		fourthRadio.add(jniv2);
		
		fourth.add(niveauDifficulte);
		fourth.add(fourthRadio);
		
		nbJoueurs.setHorizontalAlignment(JLabel.CENTER);
		nbJoueursReels.setHorizontalAlignment(JLabel.CENTER);
		nbJoueursVirtuels.setHorizontalAlignment(JLabel.CENTER);
		niveauDifficulte.setHorizontalAlignment(JLabel.CENTER);
		
		content.add(first);
		content.add(second);
		content.add(third);
		content.add(fourth);
		//this.updateStart(1, 2);
		return content;
	}

}
