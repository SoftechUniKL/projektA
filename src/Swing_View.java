package malerarbeit;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Swing_View extends JFrame{



	private static final long serialVersionUID = 1L;
	private JLabel ueberschrift;
	private JButton kosten;


	// Raum
	private JTextField textfield_flaeche, berechne_raum1;
	private JTextField textfield_hoehe, berechne_abdeckung;
	private JLabel schriftzug_flaeche, schriftzug_hoehe, flaeche_raum1, raumangabe;
	private JRadioButton decke_streichen, decke_nichtstreichen;


	// Boden
	private JLabel flaeche_abdeckung;
	private JRadioButton folieButton, kreppapierButton, kartonButton;
	double preis_folie = 0.5;
	double preis_kreppapier = 0.7;
	double preis_karton = 1.0;

	// Bundesland
	private JComboBox bundeslandAuswahl;
	private JLabel farbpanel_titel;
	private JPanel farbe;
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

	// Rauchen
	private JRadioButton yes, no;
	private JTextField raucherwohnung_folge;

	//Anzahl der Räume
	private JList raum;
	private final String zimmer[] = { "1 Zimmer", "2 Zimmer", "3 Zimmer", "4 Zimmer", 
			"5 Zimmer", "6 Zimmer", "7 Zimmer", "8 Zimmer", "9 Zimmer"};

	//Farbe
	private JRadioButton dispersionsfarbe, latex_seidenglanz, schadstofffarbe;
	private JLabel farbpreis_proliter, benötigte_farbe;
	private JTextField farbpreis_proliter_tf, benötigte_farbe_tf;

	// Berechnung
	double preis_dispersionsfarbe = 3.0; // jeweils pro Liter Farbe
	double preis_latex_seidenglanz = 4.0;
	double preis_schadstofffarbe = 5.0;
	double deckkraft = 7.0; // Quadratmeter pro Liter (für alle Farben gleich)
	double arbeitsleistung = 10.0; // Quadratmeter pro Stunde
	double ergebnis;


	public Swing_View(Swing_Logic stl) {
		super("Malerkosten");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

		ueberschrift = new JLabel ("Sonstige Angaben:");
		ueberschrift.setBounds(395,0,400,25);
		getContentPane().add(ueberschrift);

		add_room();
		radio_button();
		combo_box_bundesland();
		raum_anzahl();
		farbe();
		raucherwohnung();

		kosten_berechnen();
	}



	private void add_room() {

		// Fenster intitialisieren
		raumangabe = new JLabel("Angaben zur Wohnfläche:");
		schriftzug_flaeche = new JLabel("(1) Wie groß ist die Wohnung?");
		flaeche_raum1 = new JLabel("~ zu streichende Gesamtfläche der Wohnung (in m²):");
		schriftzug_hoehe = new JLabel("(2) Durchschnittliche Deckenhöhe:");
		textfield_flaeche = new JTextField("Angabe in m²");
		textfield_hoehe = new JTextField("Angabe in m");
		berechne_raum1 = new JTextField();

		// Positionen festlegen
		raumangabe.setBounds(0,0,400,25);
		schriftzug_flaeche.setBounds(5,30,400,25);
		textfield_flaeche.setBounds(5,50,100,25);
		schriftzug_hoehe.setBounds(5,80,400,25);
		textfield_hoehe.setBounds(5,100,100,25);
		flaeche_raum1.setBounds(5,430,400,25);
		berechne_raum1.setBounds(5,450,150,25);

		// Elemente zum Fenster hinzufuegen
		getContentPane().add(raumangabe);
		getContentPane().add(schriftzug_flaeche);
		getContentPane().add(schriftzug_hoehe);
		getContentPane().add(textfield_flaeche);
		getContentPane().add(textfield_hoehe);
		getContentPane().add(flaeche_raum1);
		getContentPane().add(berechne_raum1);

		// Decke
		decke_streichen = new JRadioButton("Ja", false);
		decke_nichtstreichen = new JRadioButton("Nein", false);
		ButtonGroup group = new ButtonGroup (); 
		group.add(decke_streichen);
		group.add(decke_nichtstreichen);

		JPanel radioButton_panel = new JPanel();
		radioButton_panel.setLayout(new GridLayout(1,2));
		radioButton_panel.add(decke_streichen);
		radioButton_panel.add(decke_nichtstreichen);

		radioButton_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "(3) Soll die Decke gestrichen werden?"));
		radioButton_panel.setBackground(Color.white);
		getContentPane().add(radioButton_panel);
		radioButton_panel.setBounds(5,140,250,50);

		// Action Listener
		/*
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
		 */


		/*
		// Problem hier: mehrfaches Anklicken addiert immer wieder die Raumfläche dazu!!
		decke_streichen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent streichen) {
				double a = Double.parseDouble(textfield_flaeche.getText());
				double zwischenwert;
				double gesamt;
				try{
					if(decke_streichen.isSelected()) {
						zwischenwert = Double.parseDouble(berechne_raum1.getText());
						gesamt = zwischenwert + a;
						berechne_raum1.setText(String.valueOf(gesamt));

					}
				} catch (NumberFormatException format){
						berechne_raum1.setText("Es fehlen noch Angaben");
				}
			}
		});
		 */


	}



	private void radio_button() {

		folieButton = new JRadioButton("Folie (0,50€/m²)", false);
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
				BorderFactory.createEtchedBorder(), "(5) Abdeckmaterial wählen:"));
		radioButton_panel.setBackground(Color.white);
		getContentPane().add(radioButton_panel);
		radioButton_panel.setBounds(400,30,190,80);

		// Ergebnis Ausgabe
		flaeche_abdeckung = new JLabel("Kosten für Abdeckung");
		berechne_abdeckung = new JTextField();
		flaeche_abdeckung.setBounds(670,20,400,25);
		berechne_abdeckung.setBounds(670,50,120,25);

		getContentPane().add(flaeche_abdeckung);
		getContentPane().add(berechne_abdeckung);	

		folieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent folie) {
				try{
					double squaremeter = Double.parseDouble(textfield_flaeche.getText());
					if(folieButton.isSelected()) {
						double preis_1 = preis_folie * squaremeter;
						String s1 = String.valueOf(preis_1);
						berechne_abdeckung.setText(s1);
					}
				} catch (NumberFormatException fol){
					berechne_abdeckung.setText("Falsches Eingabeformat!");
				}
			}
		});

		kreppapierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent kreppapier) {
				try {
					double squaremeter = Double.parseDouble(textfield_flaeche.getText());

					if (kreppapierButton.isSelected()){
						double preis_2 = preis_kreppapier * squaremeter;
						String s2 = String.valueOf(preis_2);
						berechne_abdeckung.setText(s2);
					}
				}catch(NumberFormatException krep){
					berechne_abdeckung.setText("Falsches Eingabeformat!");
				}
			}
		});

		kartonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent karton) {
				try {
					double squaremeter = Double.parseDouble(textfield_flaeche.getText());

					if (kartonButton.isSelected()){
						double preis_3 = preis_karton * squaremeter;
						String s3 = String.valueOf(preis_3);
						berechne_abdeckung.setText(s3);
					}
				} catch (NumberFormatException kart){
					berechne_abdeckung.setText("Falsches Eingabeformat!");
				}
			}
		});

	}



	private void combo_box_bundesland() {

		farbe = new JPanel();
		farbpanel_titel = new JLabel("(6) Aus welchem Bundesland kommen Sie?");
		farbe.add(farbpanel_titel);

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
		farbe.add(bundeslandAuswahl);
		getContentPane().add(farbe);
		farbe.setBounds(400,110,250,60);

		// Stundenlohn (View)
		schriftzug_stundenlohn = new JLabel("regionaler Stundenlohn:");
		textfield_stundenlohn = new JTextField("12.8");
		schriftzug_stundenlohn.setBounds(670,110,400,25);
		textfield_stundenlohn.setBounds(670,140,120,25);
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


	private void farbe() {

		dispersionsfarbe = new JRadioButton("Dispersionsfarbe", false);
		latex_seidenglanz = new JRadioButton("Latex seidenglanz", false);
		schadstofffarbe = new JRadioButton("Schadstoff- und geruchsabbauende Innenfarbe", false);


		ButtonGroup group = new ButtonGroup (); 
		group.add(dispersionsfarbe);
		group.add(latex_seidenglanz);
		group.add(schadstofffarbe);


		JPanel radioButton_panel = new JPanel();
		radioButton_panel.setLayout(new GridLayout(3,1));
		radioButton_panel.add(dispersionsfarbe);
		radioButton_panel.add(latex_seidenglanz);
		radioButton_panel.add(schadstofffarbe);


		radioButton_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "(7) Farbe:"));
		radioButton_panel.setBackground(Color.white);
		getContentPane().add(radioButton_panel);
		radioButton_panel.setBounds(400,320,350,100);


		farbpreis_proliter = new JLabel("Preis/l Farbe [€]:");
		benötigte_farbe = new JLabel("Gesamt Benötigte Farbe [l]:");
		getContentPane().add(farbpreis_proliter);
		getContentPane().add(benötigte_farbe);
		farbpreis_proliter.setBounds(400,430,180,25);
		benötigte_farbe.setBounds(580,430,170,25);
		farbpreis_proliter_tf = new JTextField();
		benötigte_farbe_tf = new JTextField();
		getContentPane().add(farbpreis_proliter_tf);
		getContentPane().add(benötigte_farbe_tf);
		farbpreis_proliter_tf.setBounds(400,450,150,25);
		benötigte_farbe_tf.setBounds(580,450,150,25);


		dispersionsfarbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent dispersion) {
				try{
					double d = Math.round(Double.parseDouble(berechne_raum1.getText())/7.0);
					farbpreis_proliter_tf.setText(String.valueOf(preis_dispersionsfarbe));
					benötigte_farbe_tf.setText(String.valueOf(d));
				} catch (NumberFormatException d) {
					farbpreis_proliter_tf.setText("Angaben fehlerhaft");
					benötigte_farbe_tf.setText("Angaben fehlerhaft");
				}
			}
		});

		latex_seidenglanz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent latex) {
				try{
					double l = Math.round(Double.parseDouble(berechne_raum1.getText())/7.0);
					farbpreis_proliter_tf.setText(String.valueOf(preis_latex_seidenglanz));
					benötigte_farbe_tf.setText(String.valueOf(l));
				} catch (NumberFormatException l) {
					farbpreis_proliter_tf.setText("Angaben fehlerhaft");
					benötigte_farbe_tf.setText("Angaben fehlerhaft");
				}
			}
		});

		schadstofffarbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent schadstoff) {
				try {
					double s = Math.round(Double.parseDouble(berechne_raum1.getText())/7.0);
					farbpreis_proliter_tf.setText(String.valueOf(preis_schadstofffarbe));
					benötigte_farbe_tf.setText(String.valueOf(s));
				} catch (NumberFormatException s) {
					farbpreis_proliter_tf.setText("Angaben fehlerhaft");
					benötigte_farbe_tf.setText("Angaben fehlerhaft");
				}
			}
		});


	}


	private void raucherwohnung() {

		yes = new JRadioButton("Ja", false);
		no = new JRadioButton("Nein", false);

		ButtonGroup group = new ButtonGroup (); 
		group.add(yes);
		group.add(no);

		JPanel radioButton_panel = new JPanel();
		radioButton_panel.setLayout(new GridLayout(1,2));
		radioButton_panel.add(yes);
		radioButton_panel.add(no);
		radioButton_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "(8) Wird in der Wohnung geraucht?"));
		radioButton_panel.setBackground(Color.white);
		getContentPane().add(radioButton_panel);
		radioButton_panel.setBounds(950,20,250,50);

		// Was macht das für einen Unterschied für uns?
		raucherwohnung_folge = new JTextField();
		raucherwohnung_folge.setBounds(950,75,250,25);
		getContentPane().add(raucherwohnung_folge);

		// Action Listener
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent rauchen) {
				raucherwohnung_folge.setText("Sie müssen mit 10% extra Farbe rechnen!");
			}
		});

		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent nicht_rauchen) {
				raucherwohnung_folge.setText("Keine zusätzliche Menge Farbe nötig!");
			}
		});

	}



	void raum_anzahl(){

		raum = new JList(zimmer);

		// nur eine Auswahl möglich
		raum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel r = new JPanel();
		r.setBackground(Color.white);
		r.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "(4) Anzahl der Räume wählen:"));
		r.add(raum);
		getContentPane().add(r);
		r.setBounds(5,200,190,200);


		// Annahme: Jeder Raum gleich groß und quadratisch
		// gesamt zu streichende Fläche = sqrt[Gesamtgröße/anzahl_räume] * höhe * 4 * anzahl_räume

		raum.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent k) {

				double gesamtflaeche;
				double gesamtflaeche_neu;
				String str = (String) raum.getSelectedValue();

				if (str.equals("1 Zimmer")){
					gesamtflaeche = Math.sqrt(Double.parseDouble(textfield_flaeche.getText()) / 1)
							* Double.parseDouble(textfield_hoehe.getText()) * 4 * 1;
					berechne_raum1.setText(String.valueOf(gesamtflaeche));
					if (decke_streichen.isSelected()) {
						gesamtflaeche_neu = gesamtflaeche + 100.0;
						berechne_raum1.setText(String.valueOf(gesamtflaeche_neu));
					}
				}
				if (str.equals("2 Zimmer")){
					gesamtflaeche = Math.sqrt(Double.parseDouble(textfield_flaeche.getText()) / 2)
							* Double.parseDouble(textfield_hoehe.getText()) * 4 * 2;
					berechne_raum1.setText(String.valueOf(gesamtflaeche));
					if (decke_streichen.isSelected()) {
						gesamtflaeche_neu = gesamtflaeche + 100.0;
						berechne_raum1.setText(String.valueOf(gesamtflaeche_neu));
					}
				}
				if (str.equals("3 Zimmer")){
					gesamtflaeche = Math.sqrt(Double.parseDouble(textfield_flaeche.getText()) / 3)
							* Double.parseDouble(textfield_hoehe.getText()) * 4 * 3;
					berechne_raum1.setText(String.valueOf(gesamtflaeche));
					if (decke_streichen.isSelected()) {
						gesamtflaeche_neu = gesamtflaeche + 100.0;
						berechne_raum1.setText(String.valueOf(gesamtflaeche_neu));
					}
				}
				if (str.equals("4 Zimmer")){
					gesamtflaeche = Math.sqrt(Double.parseDouble(textfield_flaeche.getText()) / 4)
							* Double.parseDouble(textfield_hoehe.getText()) * 4 * 4;
					berechne_raum1.setText(String.valueOf(gesamtflaeche));
					if (decke_streichen.isSelected()) {
						gesamtflaeche_neu = gesamtflaeche + 100.0;
						berechne_raum1.setText(String.valueOf(gesamtflaeche_neu));
					}
				}
				if (str.equals("5 Zimmer")){
					gesamtflaeche = Math.sqrt(Double.parseDouble(textfield_flaeche.getText()) / 5)
							* Double.parseDouble(textfield_hoehe.getText()) * 4 * 5;
					berechne_raum1.setText(String.valueOf(gesamtflaeche));
					if (decke_streichen.isSelected()) {
						gesamtflaeche_neu = gesamtflaeche + 100.0;
						berechne_raum1.setText(String.valueOf(gesamtflaeche_neu));
					}
				}
				if (str.equals("6 Zimmer")){
					gesamtflaeche = Math.sqrt(Double.parseDouble(textfield_flaeche.getText()) / 6)
							* Double.parseDouble(textfield_hoehe.getText()) * 4 * 6;
					berechne_raum1.setText(String.valueOf(gesamtflaeche));
					if (decke_streichen.isSelected()) {
						gesamtflaeche_neu = gesamtflaeche + 100.0;
						berechne_raum1.setText(String.valueOf(gesamtflaeche_neu));
					}
				}
				if (str.equals("7 Zimmer")){
					gesamtflaeche = Math.sqrt(Double.parseDouble(textfield_flaeche.getText()) / 7)
							* Double.parseDouble(textfield_hoehe.getText()) * 4 * 7;
					berechne_raum1.setText(String.valueOf(gesamtflaeche));
					if (decke_streichen.isSelected()) {
						gesamtflaeche_neu = gesamtflaeche + 100.0;
						berechne_raum1.setText(String.valueOf(gesamtflaeche_neu));
					}
				}
				if (str.equals("8 Zimmer")){
					gesamtflaeche = Math.sqrt(Double.parseDouble(textfield_flaeche.getText()) / 8)
							* Double.parseDouble(textfield_hoehe.getText()) * 4 * 8;
					berechne_raum1.setText(String.valueOf(gesamtflaeche));
					if (decke_streichen.isSelected()) {
						gesamtflaeche_neu = gesamtflaeche + 100.0;
						berechne_raum1.setText(String.valueOf(gesamtflaeche_neu));
					}
				}
				if (str.equals("9 Zimmer")){
					gesamtflaeche = Math.sqrt(Double.parseDouble(textfield_flaeche.getText()) / 9)
							* Double.parseDouble(textfield_hoehe.getText()) * 4 * 9;
					berechne_raum1.setText(String.valueOf(gesamtflaeche));
					if (decke_streichen.isSelected()) {
						gesamtflaeche_neu = gesamtflaeche + 100.0;
						berechne_raum1.setText(String.valueOf(gesamtflaeche_neu));
					}
				}

			}	
		}); 



	}


	void kosten_berechnen (){

		kosten = new JButton("(9) Gesamtkosten berechnen");
		kosten.setBounds(5,600,200,25);
		getContentPane().add(kosten);

		JLabel gesamtkosten = new JLabel("Sie müssen mit folgenden Kosten rechnen:");

		final JTextField gesamt = new JTextField();
		gesamtkosten.setBounds(230,600,250,25);
		getContentPane().add(gesamtkosten);
		gesamt.setBounds(485,600,100,25);
		getContentPane().add(gesamt);		

		/*
		 * Gesamtkosten = 
		 * 			(	gesamt benötigte Farbe
		 * 				* Preis/l Farbe
		 * 				+ Abdeckungskosten
		 * 				+ regionaler Stundenlohn * benötigte Zeit für die Streicharbeit   )
		 * 				
		 * 			* 1,1 (wenn Raucherwohnung)
		 * 
		 * 
		 */

		kosten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent gesamt_kosten) {
				try {
					if(yes.isSelected()){
						ergebnis = Math.round( Double.parseDouble(benötigte_farbe_tf.getText())
								* Double.parseDouble(farbpreis_proliter_tf.getText())
								+ Double.parseDouble(berechne_abdeckung.getText())
								+ (		Double.parseDouble(textfield_stundenlohn.getText()) 
										* (Double.parseDouble(berechne_raum1.getText())	/ arbeitsleistung)		) *1.1
								);
						gesamt.setText(String.valueOf(ergebnis));
					}
					else{
						ergebnis = Math.round( Double.parseDouble(benötigte_farbe_tf.getText())
								* Double.parseDouble(farbpreis_proliter_tf.getText())
								+ Double.parseDouble(berechne_abdeckung.getText())
								+ (		Double.parseDouble(textfield_stundenlohn.getText()) 
										* (Double.parseDouble(berechne_raum1.getText())	/ arbeitsleistung)		)
								);
						gesamt.setText(String.valueOf(ergebnis));
					}
				}catch (NumberFormatException erg) {
					gesamt.setText(String.valueOf("Aktion nicht möglich!"));
				}


			}
		});

	}
}

