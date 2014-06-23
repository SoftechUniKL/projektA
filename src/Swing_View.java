package malerarbeit;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Swing_View extends JFrame{

	private static final long serialVersionUID = 1L;

	// Raum
	private JTextField textfield_flaeche, berechne_raum1;
	private JTextField textfield_hoehe, berechne_abdeckung;
	private JButton bestätigung_raum1;
	private JLabel schriftzug_flaeche, schriftzug_hoehe, flaeche_raum1;
	
	// Boden
	private JLabel flaeche_abdeckung;
	private JRadioButton folieButton, kreppapierButton, kartonButton;
	double preis_folie = 0.5;
	double preis_kreppapier = 0.7;
	double preis_karton = 1.0;
	
	// Bundesland
	private JComboBox bundeslandAuswahl;
	private JLabel frage;
	private JPanel panel_bundesland;
	private JLabel schriftzug_stundenlohn;
	private JTextField textfield_stundenlohn;
	/*
	 * Malerkosten:
	 * http://malerundlackiererinfo.de/so-viel-gehalt-bekommen-maler-und-lackierer/
	   (Annahme: 22 Arbeitstage/Monat)
		*/
	double Sachsen_Anhalt = 8.66;
	double Saarland = 9.18;
	double Brandenburg = 9.59;
	double Niedersachsen = 10.02; 
	double Thueringen = 10.04;
	double Berlin = 10.09;
	double Hamburg = 10.23;
	double Mecklenburg_Vorpommern = 10.85;
	double Sachsen = 11.36;
	double Schleswig_Holstein = 11.36;
	double Nordrhein_Westfalen = 11.58;
	double Bayern = 11.72;
	double Bremen = 12.10;
	double Hessen = 12.15;
	double Baden_Wuerttemberg = 12.80;
	double Rheinland_Pfalz = 14.20;

	
	public Swing_View(Swing_Logic stl) {
		super("Malerkosten");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		add_room();
		radio_button();
		combo_box_bundesland();
	}
	
	
	
	private void add_room() {

		// Fenster intitialisieren
		schriftzug_flaeche = new JLabel("Wie groß ist der Raum?");
		flaeche_raum1 = new JLabel("Zu streichende Fläche des Raumes (in m²):");
		schriftzug_hoehe = new JLabel("Wie hoch ist die Decke?");
		textfield_flaeche = new JTextField("Angabe in m²");
		textfield_hoehe = new JTextField("Angabe in m");
		bestätigung_raum1 = new JButton("Eingaben bestätigen");
		berechne_raum1 = new JTextField();

		// Positionen festlegen
		schriftzug_flaeche.setBounds(5,10,400,25);
		textfield_flaeche.setBounds(5,30,100,25);
		schriftzug_hoehe.setBounds(5,60,400,25);
		textfield_hoehe.setBounds(5,80,100,25);
		bestätigung_raum1.setBounds(5,120,150,25);
		flaeche_raum1.setBounds(5,160,400,25);
		berechne_raum1.setBounds(5,180,150,25);

		// Elemente zum Fenster hinzufuegen
		getContentPane().add(schriftzug_flaeche);
		getContentPane().add(schriftzug_hoehe);
		getContentPane().add(textfield_flaeche);
		getContentPane().add(textfield_hoehe);
		getContentPane().add(bestätigung_raum1);
		getContentPane().add(flaeche_raum1);
		getContentPane().add(berechne_raum1);


		bestätigung_raum1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Annahme: Jeder Raum ist quadratisch
				//if (fläche.getSource() == bestätigung_raum1) {
				try {
					double a = Double.parseDouble(textfield_flaeche.getText());
					double b = Double.parseDouble(textfield_hoehe.getText());
					// gesamte Fläche (ohne Decke)
					int sum = (int) (4 * (Math.sqrt(a) * b)); // Abrunden
					String sum2 = String.valueOf(sum);
					berechne_raum1.setText(sum2);
				}
				catch(NumberFormatException ex) {
					berechne_raum1.setText("Falsches Eingabeformat!");
				}
			}
		});
		
	}


	private void radio_button() {

		folieButton = new JRadioButton("Folie (0,50€/m²)", true);
		kreppapierButton = new JRadioButton("Kreppapier (0,70€/m²)", false);
		kartonButton = new JRadioButton("Karton (1,00€/m²)", false);

		ButtonGroup group = new ButtonGroup (); 
		group.add(folieButton);
		group.add(kreppapierButton);
		group.add(kartonButton);

		JPanel radioButton_panel = new JPanel();
		radioButton_panel.setLayout(new GridLayout(3,1));
		radioButton_panel.add(folieButton);
		radioButton_panel.add(kreppapierButton);
		radioButton_panel.add(kartonButton);

		radioButton_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Abdeckmaterial wählen:"));
		radioButton_panel.setBackground(Color.white);
		getContentPane().add(radioButton_panel);
		radioButton_panel.setBounds(5,220,170,80);


		// Ergebnis Ausgabe
		flaeche_abdeckung = new JLabel("Kosten für Abdeckung der gesamten Wohnfläche:");
		berechne_abdeckung = new JTextField();
		flaeche_abdeckung.setBounds(5,320,400,25);
		berechne_abdeckung.setBounds(5,340,150,25);
		getContentPane().add(flaeche_abdeckung);
		getContentPane().add(berechne_abdeckung);	
		
		

		folieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent folie) {
				
				double squaremeter = Double.parseDouble(textfield_flaeche.getText());
				
				if(folieButton.isSelected()) {
					double preis_1 = preis_folie * squaremeter;
					String s1 = String.valueOf(preis_1);
					berechne_abdeckung.setText(s1);
				}
			}
		});


		kreppapierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent kreppapier) {
				
				double squaremeter = Double.parseDouble(textfield_flaeche.getText());
				
				if (kreppapierButton.isSelected()){
					double preis_2 = preis_kreppapier * squaremeter;
					String s2 = String.valueOf(preis_2);
					berechne_abdeckung.setText(s2);
				}
			}
		});

		kartonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent karton) {
				
				double squaremeter = Double.parseDouble(textfield_flaeche.getText());
				
				if (kartonButton.isSelected()){
					double preis_3 = preis_karton * squaremeter;
					String s3 = String.valueOf(preis_3);
					berechne_abdeckung.setText(s3);
				}
			}
		});


	}


	private void combo_box_bundesland() {

		panel_bundesland = new JPanel();
		frage = new JLabel("Aus welchem Bundesland kommen Sie?");
		panel_bundesland.add(frage);

		// Array für unsere JComboBox
		String comboBoxListe[] = {"Baden-Württemberg", "Bayern",
				"Berlin", "Brandenburg", "Bremen",
				"Hamburg", "Hessen", "Mecklenburg-Vorpommern",
				"Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz",
				"Saarland", "Sachsen", "Sachsen-Anhalt",
				"Schleswig-Holstein", "Thüringen"};

		//JComboBox mit Bundesländer-Einträgen wird erstellt
		bundeslandAuswahl = new JComboBox(comboBoxListe);

		//JComboBox wird Panel hinzugefügt
		panel_bundesland.add(bundeslandAuswahl);
		getContentPane().add(panel_bundesland);
		panel_bundesland.setBounds(300,10,250,60);
		
		
		// Stundenlohn (View)
		schriftzug_stundenlohn = new JLabel("regionaler Stundenlohn:");
		textfield_stundenlohn = new JTextField();
		schriftzug_stundenlohn.setBounds(600,10,400,25);
		textfield_stundenlohn.setBounds(600,40,50,25);
		getContentPane().add(schriftzug_stundenlohn);
		getContentPane().add(textfield_stundenlohn);
		
		
		// Action Listener Stundenlohn
		bundeslandAuswahl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switch (bundeslandAuswahl.getSelectedIndex()) {

				case 0:		textfield_stundenlohn.setText(String.valueOf(Baden_Wuerttemberg));
				break;
				case 1:		textfield_stundenlohn.setText(String.valueOf(Bayern));
				break;
				case 2:		textfield_stundenlohn.setText(String.valueOf(Berlin));
				break;
				case 3:		textfield_stundenlohn.setText(String.valueOf(Brandenburg));
				break;
				case 4:		textfield_stundenlohn.setText(String.valueOf(Bremen));
				break;
				case 5:		textfield_stundenlohn.setText(String.valueOf(Hamburg));
				break;
				case 6:		textfield_stundenlohn.setText(String.valueOf(Hessen));
				break;
				case 7:		textfield_stundenlohn.setText(String.valueOf(Mecklenburg_Vorpommern));
				break;
				case 8:		textfield_stundenlohn.setText(String.valueOf(Niedersachsen));
				break;
				case 9:		textfield_stundenlohn.setText(String.valueOf(Nordrhein_Westfalen));
				break;
				case 10:	textfield_stundenlohn.setText(String.valueOf(Rheinland_Pfalz));
				break;
				case 11:	textfield_stundenlohn.setText(String.valueOf(Saarland));
				break;
				case 12:	textfield_stundenlohn.setText(String.valueOf(Sachsen));
				break;
				case 13:	textfield_stundenlohn.setText(String.valueOf(Sachsen_Anhalt));
				break;
				case 14:	textfield_stundenlohn.setText(String.valueOf(Schleswig_Holstein));
				break;
				case 15:	textfield_stundenlohn.setText(String.valueOf(Thueringen));
				break;
				default:    textfield_stundenlohn.setText("Fehler");
				}
			}
		});	
		
	}
}
