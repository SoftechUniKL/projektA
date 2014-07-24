package malerarbeit;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Window;
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

	Renovierung r = new Renovierung(this);
	Wohnung w = new Wohnung(this);
	
	
	private static final long serialVersionUID = 1L;
	
	private JLabel sonstiges;
	private JButton kosten;


	// Raum
	private JTextField tf_flaeche, tf_raumberechnung, tf_hoehe, tf_berechne_abdeckung;
	private JLabel schriftzug_flaeche, schriftzug_hoehe, flaeche_raum1, wohnangaben;
	private JRadioButton decke_streichen, decke_nichtstreichen;
	
	// Boden
	private JLabel flaeche_abdeckung;
	private JRadioButton folieButton, kreppapierButton, kartonButton;
	
	// Bundesland
	private JComboBox bundeslandAuswahl;
	private JLabel bundesland, schriftzug_stundenlohn;
	private JPanel bundesland_panel;
	private JTextField tf_stundenlohn;
	
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
	private JTextField tf_farbpreis_proliter, tf_benötigte_farbe;


	public Swing_View() {
		
		super("Malerkosten");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

		allgemeines();
		add_room();
		abdeckung();
		combo_box_bundesland();
		raum_anzahl();
		farbe();
		raucherwohnung();
		kosten();
	}


	public void allgemeines() {
		
		wohnangaben = new JLabel("Angaben zur Wohnfläche:");
		wohnangaben.setBounds(0,0,400,25);
		getContentPane().add(wohnangaben);
		
		sonstiges = new JLabel ("Sonstige Angaben:");
		sonstiges.setBounds(395,0,400,25);
		getContentPane().add(sonstiges);
	}
	

	public void add_room() {

		// Fenster intitialisieren
		schriftzug_flaeche = new JLabel("(1) Wie groß ist die Wohnung?");
		flaeche_raum1 = new JLabel("~ zu streichende Gesamtfläche der Wohnung (in m²):");
		schriftzug_hoehe = new JLabel("(2) Durchschnittliche Deckenhöhe:");
		tf_flaeche = new JTextField("Angabe in m²");
		tf_hoehe = new JTextField("Angabe in m");
		tf_raumberechnung = new JTextField();

		// Positionen festlegen
		schriftzug_flaeche.setBounds(5,30,400,25);
		tf_flaeche.setBounds(5,50,100,25);
		schriftzug_hoehe.setBounds(5,80,400,25);
		tf_hoehe.setBounds(5,100,100,25);
		flaeche_raum1.setBounds(5,430,400,25);
		tf_raumberechnung.setBounds(5,450,150,25);

		// Elemente zum Fenster hinzufuegen
		getContentPane().add(schriftzug_flaeche);
		getContentPane().add(schriftzug_hoehe);
		getContentPane().add(tf_flaeche);
		getContentPane().add(tf_hoehe);
		getContentPane().add(flaeche_raum1);
		getContentPane().add(tf_raumberechnung);

		// Decke streichen?
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
		radioButton_panel.setBounds(5,135,250,50);
		getContentPane().add(radioButton_panel);
	}
	

	private void abdeckung() {

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

		// Kosten für Abdeckung:
		flaeche_abdeckung = new JLabel("Kosten für Abdeckung");
		tf_berechne_abdeckung = new JTextField();
		flaeche_abdeckung.setBounds(670,20,400,25);
		tf_berechne_abdeckung.setBounds(670,50,120,25);

		getContentPane().add(flaeche_abdeckung);
		getContentPane().add(tf_berechne_abdeckung);	

		
		folieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent folie) {
				try{
					if(folieButton.isSelected()){
						r.setMaterialkosten(0.5);
						r.setQuadratmeter(Double.parseDouble(getSquaremeter()));
						r.abzudeckende_fläche();
					}	
				} catch (NumberFormatException fol){
					tf_berechne_abdeckung.setText("Falsches Eingabeformat!");
				}
			}
		});
		
		kreppapierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent kreppapier) {
				try{
					if(kreppapierButton.isSelected()){
						r.setMaterialkosten(0.7);
						r.setQuadratmeter(Double.parseDouble(getSquaremeter()));
						r.abzudeckende_fläche();
					}	
				} catch (NumberFormatException krep){
					tf_berechne_abdeckung.setText("Falsches Eingabeformat!");
				}
			}
		});
		
		kartonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent karton) {
				try{
					if(kartonButton.isSelected()){
						r.setMaterialkosten(1.0);
						r.setQuadratmeter(Double.parseDouble(getSquaremeter()));
						r.abzudeckende_fläche();
					}	
				} catch (NumberFormatException kar){
					tf_berechne_abdeckung.setText("Falsches Eingabeformat!");
				}
			}
		});

	}



	private void combo_box_bundesland() {

		bundesland_panel = new JPanel();
		bundesland = new JLabel("(6) Aus welchem Bundesland kommen Sie?");
		bundesland_panel.add(bundesland);

		// Array für unsere JComboBox
		String comboBoxListe[] = {"-Bitte wählen-", "Baden-Württemberg", "Bayern",
				"Berlin", "Brandenburg", "Bremen",
				"Hamburg", "Hessen", "Mecklenburg-Vorpommern",
				"Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz",
				"Saarland", "Sachsen", "Sachsen-Anhalt",
				"Schleswig-Holstein", "Thüringen"};

		//JComboBox mit Bundesländer-Einträgen wird erstellt
		bundeslandAuswahl = new JComboBox(comboBoxListe);

		//JComboBox wird Panel hinzugefügt
		bundesland_panel.add(bundeslandAuswahl);
		getContentPane().add(bundesland_panel);
		bundesland_panel.setBounds(400,110,250,60);

		// Stundenlohn
		schriftzug_stundenlohn = new JLabel("regionaler Stundenlohn:");
		tf_stundenlohn = new JTextField();
		schriftzug_stundenlohn.setBounds(670,110,400,25);
		tf_stundenlohn.setBounds(670,140,120,25);
		getContentPane().add(schriftzug_stundenlohn);
		getContentPane().add(tf_stundenlohn);

		
		
		// Action Listener Stundenlohn
		bundeslandAuswahl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent bund) {
				r.stundenlohn_bundesland();
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
		tf_farbpreis_proliter = new JTextField();
		tf_benötigte_farbe = new JTextField();
		getContentPane().add(tf_farbpreis_proliter);
		getContentPane().add(tf_benötigte_farbe);
		tf_farbpreis_proliter.setBounds(400,450,150,25);
		tf_benötigte_farbe.setBounds(580,450,150,25);


		

		dispersionsfarbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent dispersion) {
				r.dispersionsfarbe();
				r.benötigte_farbe(Double.parseDouble(getzustreichendeFläche()) / r.deckkraft);
			}
		});
		latex_seidenglanz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent dispersion) {
				r.latexfarbe();
				r.benötigte_farbe(Double.parseDouble(getzustreichendeFläche()) / r.deckkraft);
			}
		});
		schadstofffarbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent dispersion) {
				r.schadstofffarbe();
				r.benötigte_farbe(Double.parseDouble(getzustreichendeFläche()) / r.deckkraft);
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

		// Was macht das für einen Unterschied, ob geraucht wird oder nicht?
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
		raum.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent k) {

				String str = (String) raum.getSelectedValue();
				for (int i = 1; i < 10; i++) {
					try{
						if(str.equals(i + " Zimmer")){
							w.setAnzahlRäume(i);
							w.setHöhe(Double.parseDouble(tf_hoehe.getText()));
							w.setQuadratmeter(Double.parseDouble(tf_flaeche.getText()));
							w.zu_streichende_fläche();
							if(decke_streichen.isSelected()){
								w.addDeckenfläche();
							}
						}
					}catch(NumberFormatException raum){
						tf_raumberechnung.setText("Falsche/Fehlende Angabe");
					}
				}
			}	
		}); 
		
		
	}


	void kosten(){

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

		/*
		kosten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent gesamt_kosten) {
				try {
					if(yes.isSelected()){
						ergebnis = Math.round( Double.parseDouble(tf_benötigte_farbe.getText())
								* Double.parseDouble(tf_farbpreis_proliter.getText())
								+ Double.parseDouble(tf_berechne_abdeckung.getText())
								+ (		Double.parseDouble(tf_stundenlohn.getText()) 
										* (Double.parseDouble(tf_raumberechnung.getText())	/ arbeitsleistung)		) *1.1
								);
						gesamt.setText(String.valueOf(ergebnis));
					}
					else{
						ergebnis = Math.round( Double.parseDouble(tf_benötigte_farbe.getText())
								* Double.parseDouble(tf_farbpreis_proliter.getText())
								+ Double.parseDouble(tf_berechne_abdeckung.getText())
								+ (		Double.parseDouble(tf_stundenlohn.getText()) 
										* (Double.parseDouble(tf_raumberechnung.getText())	/ arbeitsleistung)		)
								);
						gesamt.setText(String.valueOf(ergebnis));
					}
				}catch (NumberFormatException erg) {
					gesamt.setText(String.valueOf("Aktion nicht möglich!"));
				}


			}
		});
		
		
*/
	}

	public void setZuStreichendeFläche(double gesamtfläche){
		tf_raumberechnung.setText(Double.toString(gesamtfläche));
	}
	
	public void setKostenFürAbdeckung(double abdeckungskosten){
		tf_berechne_abdeckung.setText(Double.toString(abdeckungskosten));
	}
	
	public String getSquaremeter(){
		return tf_flaeche.getText();
	}


	public void set_tf_Stundenlohn(double stundenlohn) {
		try{
		tf_stundenlohn.setText(Double.toString(stundenlohn));
		} catch (NumberFormatException lohn){
			tf_stundenlohn.setText("Fehler");
		}
	}
	
	public int bundesland(){
		return bundeslandAuswahl.getSelectedIndex();
	}


	public void setFarbpreis(double preis_dispersionsfarbe) {
		tf_farbpreis_proliter.setText(Double.toString(preis_dispersionsfarbe));
	}

	public void setFarbmenge(double liter) {
		tf_benötigte_farbe.setText(Double.toString(liter));
	}
	
	public String getzustreichendeFläche(){
		return tf_raumberechnung.getText();
	}
	
}

