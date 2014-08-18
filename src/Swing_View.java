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

	
	/**
	 * Test
	 */
	private JLabel sonstiges;
	private JButton kosten;


	// Raum
	private JTextField tf_flaeche, tf_raum, tf_hoehe, tf_abdeckungskosten;
	private JLabel gesamtflaeche, hoehe, streichfläche, wohnangaben;
	private JRadioButton decke_streichen, decke_nichtstreichen;

	// Boden
	private JLabel abdeckungskosten;
	private JRadioButton folieButton, kreppapierButton, kartonButton;

	// Bundesland
	private JComboBox comboBox_bundesland;
	private JLabel bundesland, stundenlohn;
	private JPanel pnl_bundesland;
	private JTextField tf_stundenlohn;

	// Rauchen
	private JRadioButton yes, no;
	private JTextField tf_rauchen;

	//Anzahl der Räume
	private JList raum;
	private final String zimmer[] = { "1 Zimmer", "2 Zimmer", "3 Zimmer", "4 Zimmer", 
			"5 Zimmer", "6 Zimmer", "7 Zimmer", "8 Zimmer", "9 Zimmer"};

	//Farbe
	private JRadioButton dispersionsfarbe, latex_seidenglanz, schadstofffarbe;
	private JLabel farbpreis_proliter, benötigte_farbe;
	private JTextField tf_farbpreisproliter, tf_benötigtefarbe;

	private JTextField tf_gesamtkosten;
	boolean raucher;

	
	
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

		/**
		 * Wohnfläche angeben
		*/
		wohnangaben = new JLabel("Angaben zur Wohnfläche:");
		wohnangaben.setBounds(0,0,400,25);
		getContentPane().add(wohnangaben);

		sonstiges = new JLabel ("Sonstige Angaben:");
		sonstiges.setBounds(395,0,400,25);
		getContentPane().add(sonstiges);
	}


	public void add_room() {

		// Fenster intitialisieren
		gesamtflaeche = new JLabel("(1) Wie groß ist die Wohnung?");
		streichfläche = new JLabel("~ zu streichende Gesamtfläche der Wohnung (in m²):");
		hoehe = new JLabel("(2) Durchschnittliche Deckenhöhe:");
		tf_flaeche = new JTextField("Angabe in m²");
		tf_hoehe = new JTextField("Angabe in m");
		tf_raum = new JTextField();

		// Positionen festlegen
		gesamtflaeche.setBounds(5,30,400,25);
		tf_flaeche.setBounds(5,50,100,25);
		hoehe.setBounds(5,80,400,25);
		tf_hoehe.setBounds(5,100,100,25);
		streichfläche.setBounds(5,430,400,25);
		tf_raum.setBounds(5,450,150,25);

		// Elemente zum Fenster hinzufuegen
		getContentPane().add(gesamtflaeche);
		getContentPane().add(hoehe);
		getContentPane().add(tf_flaeche);
		getContentPane().add(tf_hoehe);
		getContentPane().add(streichfläche);
		getContentPane().add(tf_raum);

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
		abdeckungskosten = new JLabel("Kosten für Abdeckung");
		tf_abdeckungskosten = new JTextField();
		abdeckungskosten.setBounds(670,20,400,25);
		tf_abdeckungskosten.setBounds(670,50,120,25);

		getContentPane().add(abdeckungskosten);
		getContentPane().add(tf_abdeckungskosten);	


		folieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent folie) {
				try{
					if(folieButton.isSelected()){
						r.setMaterialkosten(0.5);
						r.setQuadratmeter(Double.parseDouble(getSquaremeter()));
						r.abdeckungskosten();
					}	
				} catch (NumberFormatException fol){
					tf_abdeckungskosten.setText("Falsches Eingabeformat!");
				}
			}
		});

		kreppapierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent kreppapier) {
				try{
					if(kreppapierButton.isSelected()){
						r.setMaterialkosten(0.7);
						r.setQuadratmeter(Double.parseDouble(getSquaremeter()));
						r.abdeckungskosten();
					}	
				} catch (NumberFormatException krep){
					tf_abdeckungskosten.setText("Falsches Eingabeformat!");
				}
			}
		});

		kartonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent karton) {
				try{
					if(kartonButton.isSelected()){
						r.setMaterialkosten(1.0);
						r.setQuadratmeter(Double.parseDouble(getSquaremeter()));
						r.abdeckungskosten();
					}	
				} catch (NumberFormatException kar){
					tf_abdeckungskosten.setText("Falsches Eingabeformat!");
				}
			}
		});

	}



	private void combo_box_bundesland() {

		pnl_bundesland = new JPanel();
		bundesland = new JLabel("(6) Aus welchem Bundesland kommen Sie?");
		pnl_bundesland.add(bundesland);

		// Array für unsere JComboBox
		String bundesländer[] = {"-Bitte wählen-", "Baden-Württemberg", "Bayern",
				"Berlin", "Brandenburg", "Bremen",
				"Hamburg", "Hessen", "Mecklenburg-Vorpommern",
				"Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz",
				"Saarland", "Sachsen", "Sachsen-Anhalt",
				"Schleswig-Holstein", "Thüringen"};

		//JComboBox mit Bundesländer-Einträgen wird erstellt
		comboBox_bundesland = new JComboBox(bundesländer);

		//JComboBox wird Panel hinzugefügt
		pnl_bundesland.add(comboBox_bundesland);
		getContentPane().add(pnl_bundesland);
		pnl_bundesland.setBounds(400,110,250,60);

		// Stundenlohn
		stundenlohn = new JLabel("regionaler Stundenlohn:");
		tf_stundenlohn = new JTextField();
		stundenlohn.setBounds(670,110,400,25);
		tf_stundenlohn.setBounds(670,140,120,25);
		getContentPane().add(stundenlohn);
		getContentPane().add(tf_stundenlohn);



		// Action Listener Stundenlohn
		comboBox_bundesland.addActionListener(new ActionListener() {
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
		tf_farbpreisproliter = new JTextField();
		tf_benötigtefarbe = new JTextField();
		getContentPane().add(tf_farbpreisproliter);
		getContentPane().add(tf_benötigtefarbe);
		tf_farbpreisproliter.setBounds(400,450,150,25);
		tf_benötigtefarbe.setBounds(580,450,150,25);




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
		tf_rauchen = new JTextField();
		tf_rauchen.setBounds(950,75,250,25);
		getContentPane().add(tf_rauchen);


		// Action Listener
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent rauchen) {
				tf_rauchen.setText("Sie müssen mit 10% extra Farbe rechnen!");
				raucher = true;
			}
		});

		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent nicht_rauchen) {
				tf_rauchen.setText("Keine zusätzliche Menge Farbe nötig!");
				raucher = false;
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
						tf_raum.setText("Falsche/Fehlende Angabe");
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

		tf_gesamtkosten = new JTextField();
		gesamtkosten.setBounds(230,600,250,25);
		getContentPane().add(gesamtkosten);
		tf_gesamtkosten.setBounds(485,600,100,25);
		getContentPane().add(tf_gesamtkosten);		

		kosten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent gesamt_kosten) {
				try {
					r.gesamtkosten();					
				}catch (NumberFormatException erg) {
					tf_gesamtkosten.setText(String.valueOf("Aktion nicht möglich!"));
				}
			}
		});

	}



	public void setZuStreichendeFläche(double gesamtfläche){
		tf_raum.setText(Double.toString(gesamtfläche));
	}

	public void setKostenFürAbdeckung(double abdeckungskosten){
		tf_abdeckungskosten.setText(Double.toString(abdeckungskosten));
	}


	public void set_tf_Stundenlohn(double stundenlohn) {
		try{
			tf_stundenlohn.setText(Double.toString(stundenlohn));
		} catch (NumberFormatException lohn){
			tf_stundenlohn.setText("Fehler");
		}
	}

	public int bundesland(){
		return comboBox_bundesland.getSelectedIndex();
	}


	public void setFarbpreis(double preis_dispersionsfarbe) {
		tf_farbpreisproliter.setText(Double.toString(preis_dispersionsfarbe));
	}

	public void setFarbmenge(double liter) {
		tf_benötigtefarbe.setText(Double.toString(liter));
	}

	public void setEndergebnis(double endergebnis) {
		tf_gesamtkosten.setText(Double.toString(endergebnis));
	}

	public String getzustreichendeFläche(){
		return tf_raum.getText();
	}

	public String getpreisproliter(){
		return tf_farbpreisproliter.getText();
	}

	public String getbenötigtefarbe(){
		return tf_benötigtefarbe.getText();
	}

	public String getabdeckungskosten(){
		return tf_abdeckungskosten.getText();
	}

	public String getregionalerStundenlohn(){
		return tf_stundenlohn.getText();
	}

	public String getSquaremeter(){
		return tf_flaeche.getText();
	}

}

