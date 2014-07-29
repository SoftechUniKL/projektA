package malerarbeit;

public class Wohnung {

	private int anzahl_räume;
	private double gesamtfläche; // zu streichende Fläche
	private double sqrmt; // Quadratmeter der Wohnung
	private double höhe; // Deckenhöhe


	// Objekte erzeugen
	Swing_View view1;
	Renovierung r2;



	Wohnung (Swing_View view1){
		this.view1 = view1;
	}
	Wohnung (Renovierung r2){
		this.r2 = r2;
	}


	public void setHöhe(double höhe){
		this.höhe = höhe;
	}
	
	public double getHöhe(){
		return höhe;
	}

	public void setQuadratmeter(double sqrmt){
		this.sqrmt = sqrmt;
	}

	public void setAnzahlRäume(int anzahl_räume){
		this.anzahl_räume = anzahl_räume;
	}

	public double getFläche (double fläche){
		return fläche;	
	}


	/*
	 * Gesamtfläche = sqrt(Quadratmeter/AnzahlRäume)* Höhe * 4 * AnzahlRäume
	 */
	public void zu_streichende_fläche(){
		gesamtfläche = Math.sqrt(this.sqrmt/this.anzahl_räume) * this.höhe * 4 * this.anzahl_räume;
		view1.setZuStreichendeFläche(gesamtfläche);
	}

	public void addDeckenfläche(){
		gesamtfläche += this.sqrmt;
		view1.setZuStreichendeFläche(gesamtfläche);
	}

}
