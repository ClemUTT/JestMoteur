package fr.thomas_clement.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Vue extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public Vue() {
		super("Jest (Thomas et Clément)");
		init();
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
		content.setLayout(new BorderLayout());
		
		JPanel content2 = new JPanel();
		content2.setLayout(new FlowLayout());
		
		//Boutons nombre de joueurs
		JLabel nbJoueurs = new JLabel("Nombre de joueurs");
		JLabel nbJoueursReels = new JLabel("Nombre de joueurs réels");
		JLabel nbJoueursVirtuels = new JLabel("Nombre de joueurs virtuels");
		
		ButtonGroup groupeJ = new ButtonGroup();
		ButtonGroup groupeJR = new ButtonGroup();
		ButtonGroup groupeJV = new ButtonGroup();
		
		JRadioButton j1 = new JRadioButton("1");
		JRadioButton j2 = new JRadioButton("2");
		
		JRadioButton jr1 = new JRadioButton("1");
		JRadioButton jr2 = new JRadioButton("2");
		JRadioButton jr3 = new JRadioButton("3");
		JRadioButton jr4 = new JRadioButton("4");
		
		JRadioButton jv1 = new JRadioButton("1");
		JRadioButton jv2 = new JRadioButton("2");
		JRadioButton jv3 = new JRadioButton("3");
		JRadioButton jv4 = new JRadioButton("4");
		
		groupeJ.add(j1);
		groupeJ.add(j2);
		
		groupeJR.add(jr1);
		groupeJR.add(jr2);
		groupeJR.add(jr3);
		groupeJR.add(jr4);
		
		groupeJV.add(jv1);
		groupeJV.add(jv2);
		groupeJV.add(jv3);
		groupeJV.add(jv4);
		
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
		secondRadio.add(jr1);
		secondRadio.add(jr2);
		secondRadio.add(jr3);
		secondRadio.add(jr4);
		
		second.add(nbJoueursReels);
		second.add(secondRadio);
		
		JPanel third = new JPanel();
		third.setLayout(new GridLayout(2, 1));
		JPanel thirdRadio = new JPanel();
		thirdRadio.setLayout(new FlowLayout());
		thirdRadio.add(jv1);
		thirdRadio.add(jv2);
		thirdRadio.add(jv3);
		thirdRadio.add(jv4);
		
		third.add(nbJoueursVirtuels);
		third.add(thirdRadio);
		
		nbJoueurs.setHorizontalAlignment(JLabel.CENTER);
		nbJoueursReels.setHorizontalAlignment(JLabel.CENTER);
		nbJoueursVirtuels.setHorizontalAlignment(JLabel.CENTER);
		
		content.add(first, BorderLayout.WEST);
		content.add(second, BorderLayout.CENTER);
		content.add(third, BorderLayout.EAST);
		
		return content;
	}

}
