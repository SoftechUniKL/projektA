import javax.swing.*;

public class Test {

	public static void main(String[] args) {
		
	JFrame frame = new JFrame("Titel");
	// Erzeugung eines Rahmens
	
	JButton button = new JButton ("Klicken");
	// Widget wird erzeugt (Button, Textfeld o.�.)
	
	frame.getContentPane().add(button);
	// Widget zum Rahmen hinzuf�gen (passiert nicht automatisch)
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// Fenster schlie�t bei Klick auf das Kreuz
	
	frame.setSize(300,200);
	frame.setVisible(true);
	// Rahmengr��e setzen und sichtbar machen
	
	}

}
