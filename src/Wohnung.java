package malerarbeit;

public class Wohnung {

	int anzahl_r�ume;
	double gesamtfl�che; // zu streichende Fl�che
	double sqrmt; // Quadratmeter der Wohnung
	double h�he; // Deckenh�he

	
	// Objekte erzeugen
	Swing_View view1;
	Renovierung r2;
	
	
	
	Wohnung (Swing_View view1){
		this.view1 = view1;
	}
	Wohnung (Renovierung r2){
		this.r2 = r2;
	}
	
	
	
	
	public void setH�he(double h�he){
		this.h�he = h�he;
	}
	
	public void setQuadratmeter(double sqrmt){
		this.sqrmt = sqrmt;
	}
	
	public void setAnzahlR�ume(int anzahl_r�ume){
		this.anzahl_r�ume = anzahl_r�ume;
	}
	
	public double getFl�che (double fl�che){
		return fl�che;	
	}
	
	
	/*
	 * Gesamtfl�che = sqrt(Quadratmeter/AnzahlR�ume)* H�he * 4 * AnzahlR�ume
	*/
	public void zu_streichende_fl�che(){
		gesamtfl�che = Math.sqrt(this.sqrmt/this.anzahl_r�ume) * this.h�he * 4 * this.anzahl_r�ume;
		view1.setZuStreichendeFl�che(gesamtfl�che);
	}

	public void addDeckenfl�che(){
		gesamtfl�che += this.sqrmt;
		view1.setZuStreichendeFl�che(gesamtfl�che);
	}

}