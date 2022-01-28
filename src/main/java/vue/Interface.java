package vue;


import circuitDesignerPackage.Commandes.*;
import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;

import javax.swing.*;
import java.awt.Color;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Interface {

	private JPanel entreesLabels;
	private JPanel entreesIcons;
	private JPanel sortiesLabels;
	private JPanel sortiesIcons;

	private JLayeredPane panelCircuit;
	private JTable tableVerite;
	private JPanel panelPorte1;
	private JPanel panelPorte2;
	private JPanel panelPorte3;
	private JPanel panelPorte4;
	private JPanel panelPorte5;
	private JPanel panelPorte6;
	private JPanel panelPorte7;
	private JPanel panelPorte8;	
	private JPanel panelLiaison1;
	private JPanel panelLiaison2;
	private JPanel panelLiaison3;
	private JPanel panelLiaison4;
	private JPanel panelLiaison5;
	private JPanel panelLiaison6;
	private JPanel panelLiaison7;
	private JPanel panelLiaison8;
	private JPanel panelLiaison9;

	public JFrame frame;
	public JMenuItem sauvegarderCircuitButton;
	public JMenuItem quitterButton;
	public JMenuItem nouveauCircuitButton;
	public JMenuItem ouvrirCircuitButton;
	public JButton ajouterEntreeButton;
	public JButton ajouterSortieButton;
	public JButton supprimerEntreeButton;
	public JButton supprimerSortieButton;
	public JButton definirNomEntreeButton;
	public JButton definirNomSortiebutton;
	public JButton ajouterPorteButtonET;
	public JButton ajouterPorteButtonOU;
	public JButton ajouterPorteButtonNON;
	public JButton supprimerPorteButton;
	public JButton relierEntreeButton;
	public JButton relierSortieButton;
	public JButton calculerTableButton;
	public CircuitJLayredPane panel_circuit;
	public JTextField textFieldNomComposante;



	/**
	 * Créer l'application.
	 */
	public Interface() {
		initialize();
	}



	/**
	 * Intialiser le contenu du Frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.getContentPane().setEnabled(false);
		frame.setTitle("Système de modélisation et de calcul pour les circuits logiques");
		frame.setBounds(100, 100, 1260, 735);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);

		JMenuItem btnNouveauCircuit = new JMenuItem("Nouveau circuit");
		menuFichier.add(btnNouveauCircuit);

		JMenuItem btnOuvrirCircuit = new JMenuItem("Ouvrir circuit");
		menuFichier.add(btnOuvrirCircuit);
		
		JSeparator separator_2 = new JSeparator();
		menuFichier.add(separator_2);

		panel_circuit = new CircuitJLayredPane();
		panel_circuit.setBackground(Color.WHITE);
		panel_circuit.setBounds(266, 61, 970, 325);
		panel_circuit.setOpaque(true);
		frame.getContentPane().add(panel_circuit);
		Controleur controleur=new Controleur(panel_circuit);

		textFieldNomComposante = new JTextField("Entrée");


		JMenuItem btnSauvegarderCircuit = new JMenuItem("Sauvegarder circuit");
		Command sauvegarder = new SauvgarderCircuit(panel_circuit);
		btnSauvegarderCircuit.addActionListener(e -> sauvegarder.execute());

		menuFichier.add(btnSauvegarderCircuit);

		JMenuItem btnQuitter = new JMenuItem("Quitter");
		Command quitter = new QuitterApp();
		btnQuitter.addActionListener(e -> quitter.execute());
		menuFichier.add(btnQuitter);

		JSeparator separator = new JSeparator();
		menuFichier.add(separator);

		JMenuItem exporterCircuitLogique = new JMenuItem("Exporter circuit logique");
        Command exporter = new SauvgarderCircuit(panel_circuit);
		exporterCircuitLogique.addActionListener(e -> exporter.execute());
		menuFichier.add(exporterCircuitLogique);

		JMenuItem mntmExporterTableDe = new JMenuItem("Exporter table de vérité");
		menuFichier.add(mntmExporterTableDe);

		mntmExporterTableDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTableToTSV jTableToTSV=new JTableToTSV(panel_circuit.getTableVerite());
				controleur.ajouterAction("ToTSV",jTableToTSV);
				e.setSource("ToTSV");
				controleur.actionPerformed(e);


			}
		});

		JSeparator separator_1 = new JSeparator();
		menuFichier.add(separator_1);

		JMenu menuEdition = new JMenu("Édition");
		menuBar.add(menuEdition);

		JMenuItem btnAnnulerOperation = new JMenuItem("Annuler dernière opération");
		menuEdition.add(btnAnnulerOperation);

		JMenuItem btnAnnulerToutesOperations = new JMenuItem("Annuler toutes les opérations");
		menuEdition.add(btnAnnulerToutesOperations);

		JMenuItem btnRetablirOperation = new JMenuItem("Rétablir dernière opération");
		menuEdition.add(btnRetablirOperation);
		frame.getContentPane().setLayout(null);





		Commande temp=new AddEntree(panel_circuit);
		controleur.addNextCommande(temp);
		controleur.forceExecute(temp,panel_circuit.getWidth()/2, panel_circuit.getHeight()/2);

		temp=new AddEntree(panel_circuit);
		controleur.addNextCommande(temp);
		controleur.forceExecute(temp,panel_circuit.getWidth()/2+100, panel_circuit.getHeight()/2+100);

		temp=new AddSortie(panel_circuit);
		controleur.addNextCommande(temp);
		controleur.forceExecute(temp,panel_circuit.getWidth()/2+200, panel_circuit.getHeight()/2+50);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(266, 440, 970, 226);
		frame.getContentPane().add(scrollPane);

		UpdateTruthTable calcul =new UpdateTruthTable(panel_circuit, scrollPane);
		controleur.addNextCommande(calcul);
		controleur.forceExecute(calcul,0,0);



		JLabel lblCircuitLogique = new JLabel("Circuit logique");
		lblCircuitLogique.setFont(new Font("Dialog", Font.BOLD, 13));
		lblCircuitLogique.setBounds(272, 29, 114, 30);
		frame.getContentPane().add(lblCircuitLogique);

		JLabel lblTableDeVerite = new JLabel("Table de vérité");
		lblTableDeVerite.setFont(new Font("Dialog", Font.BOLD, 13));
		lblTableDeVerite.setBounds(274, 408, 129, 30);
		frame.getContentPane().add(lblTableDeVerite);

		Panel panel = new Panel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(21, 30, 222, 636);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAjouter = new JLabel("Ajouter");
		lblAjouter.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAjouter.setBounds(12, 12, 70, 15);
		panel.add(lblAjouter);







		JButton btnAjouterPorteET = new JButton("PorteET");
		btnAjouterPorteET.setBounds(22, 25, 188, 32);
		panel.add(btnAjouterPorteET);
		btnAjouterPorteET.setBackground(SystemColor.controlHighlight);
		btnAjouterPorteET.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddPorteET addPorteET=new AddPorteET(panel_circuit);
				controleur.addNextCommande(addPorteET);

			}
		});

		JButton btnAjouterPorteOU = new JButton("PorteOU");
		btnAjouterPorteOU.setBounds(22, 55, 188, 32);
		panel.add(btnAjouterPorteOU);
		btnAjouterPorteOU.setBackground(SystemColor.controlHighlight);
		btnAjouterPorteOU.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddPorteOU addPorteOU=new AddPorteOU(panel_circuit);
				controleur.addNextCommande(addPorteOU);

			}
		});

		JButton btnAjouterPorteNON = new JButton("PorteNON");
		btnAjouterPorteNON.setBounds(22, 85, 188, 32);
		panel.add(btnAjouterPorteNON);
		btnAjouterPorteNON.setBackground(SystemColor.controlHighlight);
		btnAjouterPorteNON.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddPorteNOT addPorteNOT=new AddPorteNOT(panel_circuit);
				controleur.addNextCommande(addPorteNOT);


			}
		});



		JButton btnAjouterPortePersonnalise = new JButton("Porte personnalisée");
		btnAjouterPortePersonnalise.setBounds(22, 115, 188, 32);
		panel.add(btnAjouterPortePersonnalise);
		btnAjouterPortePersonnalise.setBackground(new Color(211, 211, 211));

		JButton btnAjouterUneEntree = new JButton("Entrée");
		btnAjouterUneEntree.setBackground(SystemColor.controlHighlight);
		btnAjouterUneEntree.setBounds(22, 145, 188, 32);
		panel.add(btnAjouterUneEntree);
		btnAjouterUneEntree.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEntree addEntree=new AddEntree(panel_circuit);
				controleur.addNextCommande(addEntree);

			}
		});


		JButton btnAjouterUneSortie = new JButton("Sortie");
		btnAjouterUneSortie.setBackground(new Color(211, 211, 211));
		btnAjouterUneSortie.setBounds(22, 175, 188, 32);
		panel.add(btnAjouterUneSortie);
		btnAjouterUneSortie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddSortie addSortie=new AddSortie(panel_circuit);
				controleur.addNextCommande(addSortie);

			}
		});

		JLabel lblSupprimer = new JLabel("Supprimer");
		lblSupprimer.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSupprimer.setBounds(12, 199, 94, 32);
		panel.add(lblSupprimer);



		JButton btnSupprimerPorte = new JButton("Porte");
		btnSupprimerPorte.setBounds(22, 238, 188, 32);
		panel.add(btnSupprimerPorte);
		btnSupprimerPorte.setBackground(SystemColor.controlHighlight);
		btnSupprimerPorte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeletePorte deletePorte=new DeletePorte(panel_circuit);
				controleur.addNextCommande(deletePorte);

			}
		});
		



		JLabel lblD = new JLabel("Définir un nom");
		lblD.setFont(new Font("Dialog", Font.BOLD, 15));
		lblD.setBounds(12, 378, 130, 15);
		panel.add(lblD);


		textFieldNomComposante.setFont(new Font("Dialog",Font.PLAIN,18));
		textFieldNomComposante.setBounds(22, 405, 188, 32);
		panel.add(textFieldNomComposante);
		textFieldNomComposante.setBackground(SystemColor.controlHighlight);


		AfficherTextEnContinu afficherTextEnContinu=new AfficherTextEnContinu(panel_circuit,textFieldNomComposante);
		controleur.ajouterCommandePermanente(afficherTextEnContinu);

		ChangerText changerText=new ChangerText(panel_circuit,textFieldNomComposante);
		controleur.ajouterAction("ChangerText",changerText);
		JButton btnDefinirComposante = new JButton("Changer");
		btnDefinirComposante.setBounds(22, 436, 188, 32);
		panel.add(btnDefinirComposante);
		btnDefinirComposante.setBackground(new Color(211, 211, 211));
		btnDefinirComposante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				e.setSource("ChangerText");
				controleur.actionPerformed(e);
			}
		});






		JLabel lblRelierSource = new JLabel("Relier source à");
		lblRelierSource.setFont(new Font("Dialog", Font.BOLD, 15));
		lblRelierSource.setBounds(12, 514, 147, 15);
		panel.add(lblRelierSource);


		JButton btnRelierSourceEntree = new JButton("Entrée d'une porte");
		btnRelierSourceEntree.setBackground(SystemColor.controlHighlight);
		btnRelierSourceEntree.setBounds(22, 541, 188, 32);
		panel.add(btnRelierSourceEntree);

		btnRelierSourceEntree.addActionListener(e -> {

			CommandeCompose commandeCompose =new CommandeCompose(
					new PreparerConnecteur(panel_circuit),
					new AjouterLien(panel_circuit)
			);


			controleur.addNextCommande(new PreparerConnecteur(panel_circuit),
					commandeCompose);


		});




		CalculerTableVerite calculerTableVerite=
				new CalculerTableVerite(panel_circuit,scrollPane);
		controleur.ajouterAction("TableVerite",calculerTableVerite);

		JButton btnCalculerTable = new JButton("Calculer");
		btnCalculerTable.setBackground(new Color(248, 248, 255));
		btnCalculerTable.setBounds(1137, 407, 99, 21);
		frame.getContentPane().add(btnCalculerTable);
		panel_circuit.setLayout(null);
		btnCalculerTable.addActionListener(e -> {
			e.setSource("TableVerite");
			controleur.actionPerformed(e);

		});


		JPanel entreesLabels = new JPanel();
		entreesLabels.setBounds(2, 0, 46, 325);
		entreesLabels.setName("entreesLabels");
		entreesLabels.setBackground(Color.WHITE);
		entreesLabels.setOpaque(false);
		entreesLabels.setLayout(new GridLayout(0, 1, 0, 0));
		panel_circuit.add(entreesLabels, new Integer(0));

		JPanel entreesIcons = new JPanel();
		entreesIcons.setBounds(48, 0, 46, 325);
		entreesIcons.setOpaque(false);
		entreesIcons.setName("entreesIcon");
		entreesIcons.setBackground(Color.WHITE);
		panel_circuit.add(entreesIcons, new Integer(0));
		entreesIcons.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelLiaison_1 = new JPanel();
		panelLiaison_1.setBounds(94, 0, 46, 325);
		panelLiaison_1.setOpaque(false);
		panelLiaison_1.setBackground(Color.WHITE);
		panel_circuit.add(panelLiaison_1, new Integer(0));
		panelLiaison_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelPorte_1 = new JPanel();
		panelPorte_1.setBounds(140, 0, 46, 325);
		panelPorte_1.setOpaque(false);
		panelPorte_1.setBackground(Color.WHITE);
		panel_circuit.add(panelPorte_1, new Integer(0));
		panelPorte_1.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelLiaison_2 = new JPanel();
		panelLiaison_2.setBounds(186, 0, 46, 325);
		panelLiaison_2.setOpaque(false);
		panelLiaison_2.setBackground(Color.WHITE);
		panel_circuit.add(panelLiaison_2, new Integer(0));
		panelLiaison_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelPorte_2 = new JPanel();
		panelPorte_2.setBounds(232, 0, 46, 325);
		panelPorte_2.setOpaque(false);
		panelPorte_2.setBackground(Color.WHITE);
		panel_circuit.add(panelPorte_2, new Integer(0));
		panelPorte_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLiaison_3 = new JPanel();
		panelLiaison_3.setBounds(278, 0, 46, 325);
		panelLiaison_3.setOpaque(false);
		panelLiaison_3.setBackground(Color.WHITE);
		panel_circuit.add(panelLiaison_3, new Integer(0));
		panelLiaison_3.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelPorte_3 = new JPanel();
		panelPorte_3.setBounds(324, 0, 46, 325);
		panelPorte_3.setOpaque(false);
		panelPorte_3.setBackground(Color.WHITE);
		panel_circuit.add(panelPorte_3, new Integer(0));
		panelPorte_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLiaison_4 = new JPanel();
		panelLiaison_4.setBounds(370, 0, 46, 325);
		panelLiaison_4.setOpaque(false);
		panelLiaison_4.setBackground(Color.WHITE);
		panel_circuit.add(panelLiaison_4, new Integer(0));
		panelLiaison_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelPorte_4 = new JPanel();
		panelPorte_4.setBounds(416, 0, 46, 325);
		panelPorte_4.setOpaque(false);
		panelPorte_4.setBackground(Color.WHITE);
		panel_circuit.add(panelPorte_4, new Integer(0));
		panelPorte_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLiaison_5 = new JPanel();
		panelLiaison_5.setBounds(462, 0, 46, 325);
		panelLiaison_5.setOpaque(false);
		panelLiaison_5.setBackground(Color.WHITE);
		panel_circuit.add(panelLiaison_5, new Integer(0));
		panelLiaison_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelPorte_5 = new JPanel();
		panelPorte_5.setBounds(508, 0, 46, 325);
		panelPorte_5.setOpaque(false);
		panelPorte_5.setBackground(Color.WHITE);
		panel_circuit.add(panelPorte_5, new Integer(0));
		panelPorte_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLiaison_6 = new JPanel();
		panelLiaison_6.setBounds(554, 0, 46, 325);
		panelLiaison_6.setOpaque(false);
		panelLiaison_6.setBackground(Color.WHITE);
		panel_circuit.add(panelLiaison_6, new Integer(0));
		panelLiaison_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelPorte_6 = new JPanel();
		panelPorte_6.setBounds(600, 0, 46, 325);
		panelPorte_6.setOpaque(false);
		panelPorte_6.setBackground(Color.WHITE);
		panel_circuit.add(panelPorte_6, new Integer(0));
		panelPorte_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLiaison_7 = new JPanel();
		panelLiaison_7.setBounds(646, 0, 46, 325);
		panelLiaison_7.setOpaque(false);
		panelLiaison_7.setBackground(Color.WHITE);
		panel_circuit.add(panelLiaison_7, new Integer(0));
		panelLiaison_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelPorte_7 = new JPanel();
		panelPorte_7.setBounds(692, 0, 46, 325);
		panelPorte_7.setOpaque(false);
		panelPorte_7.setBackground(Color.WHITE);
		panel_circuit.add(panelPorte_7, new Integer(0));
		panelPorte_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLiaison_8 = new JPanel();
		panelLiaison_8.setBounds(738, 0, 46, 325);
		panelLiaison_8.setOpaque(false);
		panelLiaison_8.setBackground(Color.WHITE);
		panel_circuit.add(panelLiaison_8, new Integer(0));
		panelLiaison_8.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelPorte_8 = new JPanel();
		panelPorte_8.setBounds(784, 0, 46, 325);
		panelPorte_8.setOpaque(false);
		panelPorte_8.setBackground(Color.WHITE);
		panel_circuit.add(panelPorte_8, new Integer(0));
		panelPorte_8.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelLiaison_9 = new JPanel();
		panelLiaison_9.setBounds(830, 0, 46, 325);
		panelLiaison_9.setOpaque(false);
		panelLiaison_9.setBackground(Color.WHITE);
		panel_circuit.add(panelLiaison_9, new Integer(0));
		panelLiaison_9.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel sortiesIcons = new JPanel();
		sortiesIcons.setBounds(876, 0, 46, 325);
		sortiesIcons.setOpaque(false);
		sortiesIcons.setBackground(Color.WHITE);
		panel_circuit.add(sortiesIcons, new Integer(0));
		sortiesIcons.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel sortiesLabels = new JPanel();
		sortiesLabels.setBounds(922, 0, 46, 325);
		sortiesLabels.setOpaque(false);
		sortiesLabels.setBackground(Color.WHITE);
		panel_circuit.add(sortiesLabels, new Integer(0));
		sortiesLabels.setLayout(new GridLayout(0, 1, 0, 0));

		frame.setVisible(true);

		this.sauvegarderCircuitButton = btnSauvegarderCircuit;
		this.quitterButton = btnQuitter;
		this.nouveauCircuitButton = btnNouveauCircuit;
		this.ouvrirCircuitButton = btnOuvrirCircuit;
		this.ajouterEntreeButton = btnAjouterUneEntree;
		this.ajouterSortieButton = btnAjouterUneSortie;
		this.definirNomSortiebutton = btnDefinirComposante;
		this.ajouterPorteButtonET = btnAjouterPorteET;
		this.ajouterPorteButtonNON=btnAjouterPorteNON;
		this.ajouterPorteButtonOU=btnAjouterPorteOU;
		this.supprimerPorteButton = btnSupprimerPorte;
		this.relierEntreeButton = btnRelierSourceEntree;
		this.calculerTableButton = btnCalculerTable;

		this.entreesLabels = entreesLabels;
		this.entreesIcons = entreesIcons;
		this.sortiesLabels = sortiesLabels;
		this.sortiesIcons = sortiesIcons;
		this.panelPorte1 = panelPorte_1;
		this.panelPorte2 = panelPorte_2;
		this.panelPorte3 = panelPorte_3;
		this.panelPorte4 = panelPorte_4;
		this.panelPorte5 = panelPorte_5;
		this.panelPorte6 = panelPorte_6;
		this.panelPorte7 = panelPorte_7;
		this.panelPorte8 = panelPorte_8;
		this.panelLiaison1 = panelLiaison_1;
		this.panelLiaison2 = panelLiaison_2;
		this.panelLiaison3 = panelLiaison_3;
		this.panelLiaison4 = panelLiaison_4;
		this.panelLiaison5 = panelLiaison_5;
		this.panelLiaison6 = panelLiaison_6;
		this.panelLiaison7 = panelLiaison_7;
		this.panelLiaison8 = panelLiaison_8;
		this.panelLiaison9 = panelLiaison_9;
		this.panelCircuit = panel_circuit;










	}




	public JPanel getEntreesLabels() {
		return this.entreesLabels;
	}

	public JPanel getEntreesIcons() {
		return this.entreesIcons;
	}

	public JPanel getSortiesLabels() {
		return this.sortiesLabels;
	}

	public JPanel getSortiesIcons() {
		return this.sortiesIcons;
	}
	
	public JPanel getPanelPorte1() {
		return this.panelPorte1;
	}
	
	public JPanel getPanelPorte2() {
		return this.panelPorte2;
	}
	
	public JPanel getPanelPorte3() {
		return this.panelPorte3;
	}
	
	public JPanel getPanelPorte4() {
		return this.panelPorte4;
	}
	
	public JPanel getPanelPorte5() {
		return this.panelPorte5;
	}
	
	public JPanel getPanelPorte6() {
		return this.panelPorte6;
	}
	
	public JPanel getPanelPorte7() {
		return this.panelPorte7;
	}
	
	public JPanel getPanelPorte8() {
		return this.panelPorte8;
	}
	
	public JPanel getPanelLiaison1() {
		return this.panelLiaison1;
	}
	
	public JPanel getPanelLiaison2() {
		return this.panelLiaison2;
	}
	
	public JPanel getPanelLiaison3() {
		return this.panelLiaison3;
	}
	
	public JPanel getPanelLiaison4() {
		return this.panelLiaison4;
	}
	
	public JPanel getPanelLiaison5() {
		return this.panelLiaison5;
	}
	
	public JPanel getPanelLiaison6() {
		return this.panelLiaison6;
	}
	
	public JPanel getPanelLiaison7() {
		return this.panelLiaison7;
	}
	
	public JPanel getPanelLiaison8() {
		return this.panelLiaison8;
	}
	
	public JPanel getPanelLiaison9() {
		return this.panelLiaison9;
	}


	public JLayeredPane getPanelCircuit() {
		return this.panelCircuit;
	}
	
	public JButton getBoutonCalculer() {
		return this.calculerTableButton;
	}

	public JTable getTableVerite() { return this.tableVerite; }
	public JTable setTableVerite(JTable a) { return this.tableVerite=a; }


	public JTextField getTextFieldNomComposante(){
		return this.textFieldNomComposante;
	}

}
