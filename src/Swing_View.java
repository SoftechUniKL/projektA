package malerarbeit;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Swing_View extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L; // ?

	private JTextField textfield_flaeche, berechne_raum1;
	private JTextField textfield_hoehe;
	private JButton best‰tigung_raum1;
	private JLabel schriftzug_flaeche, schriftzug_hoehe, flaeche_raum1;
	private JRadioButton pakettButton, laminatButton, teppichButton, pvcButton, sonstigeButton;

	
	
	
	public Swing_View(Swing_Logic stl) {
		super("Malerkosten");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		add_room();
		radio_button();
	}

	private void radio_button() {
		
		pakettButton = new JRadioButton("Pakett", true);
		laminatButton = new JRadioButton("Laminat", false);
		teppichButton = new JRadioButton("Teppich", false);
		pvcButton = new JRadioButton("PVC", false);
		sonstigeButton = new JRadioButton("Sonstige", false);
		
		ButtonGroup group = new ButtonGroup (); 
		group.add(pakettButton);
		group.add(laminatButton);
		group.add(teppichButton);
		group.add(pvcButton);
		group.add(sonstigeButton);
		
		JPanel radioButton_panel = new JPanel();
		radioButton_panel.setLayout(new GridLayout(5,1));
		radioButton_panel.add(pakettButton);
		radioButton_panel.add(laminatButton);
		radioButton_panel.add(teppichButton);
		radioButton_panel.add(pvcButton);
		radioButton_panel.add(sonstigeButton);
		radioButton_panel.setBorder(BorderFactory.createTitledBorder(
		           BorderFactory.createEtchedBorder(), "Bodenbelag w‰hlen:"));
		// radioButton_panel.setBounds(5,220,100,100);
	}


	private void add_room() {

		// Fenster intitialisieren
		schriftzug_flaeche = new JLabel("Wie groﬂ ist der Raum?");
		flaeche_raum1 = new JLabel("Zu streichende Fl‰che des Raumes (in m≤):");
		schriftzug_hoehe = new JLabel("Wie hoch ist die Decke?");
		textfield_flaeche = new JTextField("Angabe in m≤");
		textfield_hoehe = new JTextField("Angabe in m");
		best‰tigung_raum1 = new JButton("Eingaben best‰tigen");
		best‰tigung_raum1.addActionListener(this);
		berechne_raum1 = new JTextField();

		// Positionen festlegen
		schriftzug_flaeche.setBounds(5,10,400,25);
		textfield_flaeche.setBounds(5,30,100,25);
		schriftzug_hoehe.setBounds(5,60,400,25);
		textfield_hoehe.setBounds(5,80,100,25);
		best‰tigung_raum1.setBounds(5,120,150,25);
		flaeche_raum1.setBounds(5,160,400,25);
		berechne_raum1.setBounds(5,180,150,25);

		// Elemente zum Fenster hinzufuegen
		getContentPane().add(schriftzug_flaeche);
		getContentPane().add(schriftzug_hoehe);
		getContentPane().add(textfield_flaeche);
		getContentPane().add(textfield_hoehe);
		getContentPane().add(best‰tigung_raum1);
		getContentPane().add(flaeche_raum1);
		this.getContentPane().add(berechne_raum1);
	}

	
	
	public void actionPerformed(ActionEvent fl‰che) {

		// Annahme: Jeder Raum ist quadratisch
		if (fl‰che.getSource() == best‰tigung_raum1) {
			try {
				double a = Double.parseDouble(textfield_flaeche.getText());
				double b = Double.parseDouble(textfield_hoehe.getText());
				// gesamte Fl‰che (ohne Decke)
				int sum = (int) (4 * (Math.sqrt(a) * b)); // Abrunden
				String sum2 = String.valueOf(sum);
				berechne_raum1.setText(sum2);
			}
			catch(NumberFormatException e) {
				berechne_raum1.setText("Falsches Eingabeformat!");
			}
		}

	}
}