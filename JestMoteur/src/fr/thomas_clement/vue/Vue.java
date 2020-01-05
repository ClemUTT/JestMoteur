package fr.thomas_clement.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
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

public class Vue extends JFrame implements Observer{
	
	private static final long serialVersionUID = 1L;
	private AbstractControleur controleur;
	private JRadioButton jr[] = {new JRadioButton("0"), new JRadioButton("1"), new JRadioButton("2"), new JRadioButton("3"), new JRadioButton("4")};
	private JRadioButton jv[] = {new JRadioButton("0"), new JRadioButton("1"), new JRadioButton("2"), new JRadioButton("3"), new JRadioButton("4")};
	private JRadioButton j[] = {new JRadioButton("3"), new JRadioButton("4")};
	private ButtonGroup groupeJ = new ButtonGroup();
	private ButtonGroup groupeJR = new ButtonGroup();
	private ButtonGroup groupeJV = new ButtonGroup();
	private ButtonGroup groupeNiv[] = {new ButtonGroup(), new ButtonGroup(), new ButtonGroup(), new ButtonGroup()};
	private JRadioButton[][] nivButtons = new JRadioButton[4][2];
	private JButton btnJouer = new JButton("JOUER");
	//j1.setEnabled(false);

	public Vue(AbstractControleur controleur) {
		super("Jest (Thomas et Clément)");
		this.controleur = controleur;
		init();
	}
	
		
	@Override
	public void updateStart(int nbReels, int nbVirtuels, int nbVirtuelSelected, int nbReelSelected, boolean readyToPlay) {
		
		if(readyToPlay) {
			this.btnJouer.setEnabled(true);
		} else {
			this.btnJouer.setEnabled(false);
		}
		
		//jv[nbVirtuelSelected-1].setSelected(true);
		System.out.println(" nbVirtuelSelected " + nbVirtuelSelected);
		
		if(nbVirtuelSelected == 0) {
			for (int i = 0; i < groupeNiv.length; i++) {
				this.nivButtons[i][0].setEnabled(false);
				this.nivButtons[i][1].setEnabled(false);
			}
		} else {
			System.out.println("entré");
			for (int i = 0; i < nbVirtuelSelected; i++) {
				this.nivButtons[i][0].setEnabled(true);
				this.nivButtons[i][1].setEnabled(true);
				
			}
			
			for (int i = nbVirtuelSelected; i < groupeNiv.length; i++) {
				this.nivButtons[i][0].setEnabled(false);
				this.nivButtons[i][1].setEnabled(false);
				
			}
		}
		
		if(jv[0].isSelected()) {
			for (int i = 0; i < groupeNiv.length; i++) {
					this.groupeNiv[i].clearSelection();
			}
		}
		
		for (int i = 0; i < nbReels; i++) {
			//real players number selectionable
			this.jr[i].setEnabled(true);
		}
		
		for (int i = nbReels; i < jr.length; i++) {
			//real players number UNselectionable
			if(this.jr[i].isSelected()) {
				this.jr[0].setSelected(true);
			}
			this.jr[i].setEnabled(false);
			
		}
		
		
		for (int i = 0; i < nbVirtuels; i++) {
			//virtual players number selectionable
			this.jv[i].setEnabled(true);
		}
		
		for (int i = nbVirtuels; i < jv.length; i++) {
			//virtual players number UNselectionable
			if(this.jv[i].isSelected()) {
				this.jv[0].setSelected(true);
				for (int j = 0; j < groupeNiv.length; j++) {
					this.nivButtons[j][0].setEnabled(false);
					this.nivButtons[j][1].setEnabled(false);
					this.groupeNiv[j].clearSelection();
				}
			}
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
		content.add(this.btnJouer, BorderLayout.SOUTH);
		this.btnJouer.setEnabled(false);
		this.btnJouer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					controleur.jouer();
			}
		});
		
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
		
		
//		JRadioButton jniv1 = new JRadioButton("1");
//		JRadioButton jniv2 = new JRadioButton("2");
		
		groupeJ.add(j[0]);
		groupeJ.add(j[1]);
		
		groupeJR.add(jr[0]);
		groupeJR.add(jr[1]);
		groupeJR.add(jr[2]);
		groupeJR.add(jr[3]);
		groupeJR.add(jr[4]);
		
		groupeJV.add(jv[0]);
		groupeJV.add(jv[1]);
		groupeJV.add(jv[2]);
		groupeJV.add(jv[3]);
		groupeJV.add(jv[4]);
		
		
		this.nivButtons[0][0] = new JRadioButton("1");
		this.nivButtons[0][1] = new JRadioButton("2");
		
		this.nivButtons[1][0] = new JRadioButton("1");
		this.nivButtons[1][1] = new JRadioButton("2");
		
		this.nivButtons[2][0] = new JRadioButton("1");
		this.nivButtons[2][1] = new JRadioButton("2");
		
		this.nivButtons[3][0] = new JRadioButton("1");
		this.nivButtons[3][1] = new JRadioButton("2");
		
//		this.nivButtons[4][0] = new JRadioButton("1");
//		this.nivButtons[4][1] = new JRadioButton("2");
		
		JPanel first = new JPanel();
		first.setLayout(new GridLayout(2, 1));
		JPanel firstRadio = new JPanel();
		firstRadio.setLayout(new FlowLayout());
		firstRadio.add(j[0]);
		firstRadio.add(j[1]);
		
		
		first.add(nbJoueurs);
		first.add(firstRadio);
		
		JPanel second = new JPanel();
		second.setLayout(new GridLayout(2, 1));
		JPanel secondRadio = new JPanel();
		secondRadio.setLayout(new GridLayout(5, 1));
		secondRadio.add(jr[0]);
		secondRadio.add(jr[1]);
		secondRadio.add(jr[2]);
		secondRadio.add(jr[3]);
		secondRadio.add(jr[4]);
		
		second.add(nbJoueursReels);
		second.add(secondRadio);
		
		JPanel third = new JPanel();
		third.setLayout(new GridLayout(2, 1));
		JPanel thirdRadio = new JPanel();
		thirdRadio.setLayout(new GridLayout(5, 1));
		thirdRadio.add(jv[0]);
		thirdRadio.add(jv[1]);
		thirdRadio.add(jv[2]);
		thirdRadio.add(jv[3]);
		thirdRadio.add(jv[4]);
		
		third.add(nbJoueursVirtuels);
		third.add(thirdRadio);
		
		
		
		for (int i = 0; i < this.nivButtons.length; i++) {
			for (int j = 0; j < 2; j++) {
				//this.nivButtons[i][j] = new JRadioButton(Integer.toString(j+1));
				this.groupeNiv[i].add(this.nivButtons[i][j]);
			}
		}
		
		JPanel fourth = new JPanel();
		fourth.setLayout(new GridLayout(2, 1));
		JPanel fourthRadio = new JPanel();
		fourthRadio.setLayout(new GridLayout(4, 1));
		JPanel fourthRadioLigne1 = new JPanel(new FlowLayout());
		fourthRadioLigne1.add(this.nivButtons[0][0]);
		fourthRadioLigne1.add(this.nivButtons[0][1]);
		JPanel fourthRadioLigne2 = new JPanel(new FlowLayout());
		fourthRadioLigne2.add(this.nivButtons[1][0]);
		fourthRadioLigne2.add(this.nivButtons[1][1]);
		JPanel fourthRadioLigne3 = new JPanel(new FlowLayout());
		fourthRadioLigne3.add(this.nivButtons[2][0]);
		fourthRadioLigne3.add(this.nivButtons[2][1]);
		JPanel fourthRadioLigne4 = new JPanel(new FlowLayout());
		fourthRadioLigne4.add(this.nivButtons[3][0]);
		fourthRadioLigne4.add(this.nivButtons[3][1]);
//		JPanel fourthRadioLigne5 = new JPanel(new FlowLayout());
//		fourthRadioLigne5.add(this.nivButtons[4][0]);
//		fourthRadioLigne5.add(this.nivButtons[4][1]);
		
		fourthRadio.add(fourthRadioLigne1);
		fourthRadio.add(fourthRadioLigne2);
		fourthRadio.add(fourthRadioLigne3);
		fourthRadio.add(fourthRadioLigne4);
//		fourthRadio.add(fourthRadioLigne5);
		
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
		
		for (int i = 0; i < j.length; i++) {
			this.j[i].addItemListener(new ItemListener() {
				
				public void itemStateChanged(ItemEvent e) {
					
					if (e.getStateChange() == ItemEvent.SELECTED) {
						//System.out.println("sélectionné " + Integer.parseInt(((AbstractButton) e.getSource()).getText()));
						controleur.setNombreJ(Integer.parseInt(((AbstractButton) e.getSource()).getText()), getNiveau());
				    }
					
				}
			});
		}
		
		for (int i = 0; i < jr.length; i++) {
			this.jr[i].addItemListener(new ItemListener() {
				
				public void itemStateChanged(ItemEvent e) {
					
					if (e.getStateChange() == ItemEvent.SELECTED) {
						//System.out.println("sélectionné " + Integer.parseInt(((AbstractButton) e.getSource()).getText()));
						controleur.setNombreRJ(Integer.parseInt(((AbstractButton) e.getSource()).getText()), getNiveau());
				    }
					
				}
			});
		}
		
		for (int i = 0; i < jv.length; i++) {
			this.jv[i].addItemListener(new ItemListener() {
				
				public void itemStateChanged(ItemEvent e) {
					
					if (e.getStateChange() == ItemEvent.SELECTED) {
						//System.out.println("sélectionné " + Integer.parseInt(((AbstractButton) e.getSource()).getText()));
						controleur.setNombreVJ(Integer.parseInt(((AbstractButton) e.getSource()).getText()), getNiveau());
				    }
					
				}
			});
		}
		
		
		
		for (int i = 0; i < groupeNiv.length; i++) {
			
			for (int j = 0; j < 2; j++) {
				
				this.nivButtons[i][j].addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						
						controleur.startParty(getNiveau()[0], getNiveau()[1]);
						
					}
				});
				
			}
			
		}
		
		
		return content;
	}

	public int[] getNiveau(){
		int niv1 = 0, niv2 = 0;
		
		for (int i = 0; i < groupeNiv.length; i++) {
			for (int j = 0; j < 2; j++) {
				if(nivButtons[i][j].isSelected() && nivButtons[i][j].isEnabled()) {
					if(nivButtons[i][j].getText().equals("1")) {
						niv1 += 1;
					} else {
						niv2 += 1;
					}
				}
			}
		}
		System.out.println("ohhhhh hhéééé " + niv1 + " " + niv2);
		return new int[]{niv1, niv2};
	}

}
