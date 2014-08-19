package malerarbeit;

public class Wohnung {

	int anzahl_raeume;
	double gesamtflaeche; // zu streichende Fläche
	double sqrmt; // Quadratmeter der Wohnung
	double hoehe; // Deckenhöhe


	// Objekt erzeugen
	Swing_View view1;


	Wohnung (Swing_View view1){
		this.view1 = view1;
	}



	public void setHöhe(double hoehe){
		this.hoehe = hoehe;
	}
	
	public Double getHoehe(){
		return this.hoehe;
	}

	public void setQuadratmeter(double sqrmt){
		this.sqrmt = sqrmt;
	}
	public Double getQuadratmeter(){
		return this.sqrmt;
	}

	public void setAnzahlRaeume(int anzahl_raeume){
		this.anzahl_raeume = anzahl_raeume;
	}

	public double getFlaeche (double flaeche){
		return flaeche;	
	}


	/*
	 * Gesamtfläche = sqrt(Quadratmeter/AnzahlRäume)* Höhe * 4 * AnzahlRäume
	 */
	public void zu_streichende_flaeche(){
		gesamtflaeche = Math.sqrt(this.sqrmt/this.anzahl_raeume) * this.hoehe * 4 * this.anzahl_raeume;
		view1.setZuStreichendeFläche(gesamtflaeche);
	}

	public void addDeckenflaeche(){
		gesamtflaeche += this.sqrmt;
		view1.setZuStreichendeFläche(gesamtflaeche);
	}


}
