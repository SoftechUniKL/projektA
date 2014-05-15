import javax.swing.*;

public class Test {

	public static void main(String[] args) {
		
	JFrame frame = new JFrame("Titel");
	// Erzeugung eines Rahmens
	
	JButton button = new JButton ("Klicken");
	// Widget wird erzeugt (Button, Textfeld o.Ä.)
	
	frame.getContentPane().add(button);
	// Widget zum Rahmen hinzufügen (passiert nicht automatisch)
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// Fenster schließt bei Klick auf das Kreuz
	
	frame.setSize(300,200);
	frame.setVisible(true);
	// Rahmengröße setzen und sichtbar machen
	
	}

}
